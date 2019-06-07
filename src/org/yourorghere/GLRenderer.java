package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/*
Esto viene ya con el plugin, aparece al seleccionar
// en "Nuevo proyecto" en vez de "Java application" 
// seleccionas "JOGL Application (Form Designer, GLCanvas)
 y ya te trae todo varias cosas implementadas
*/
//----------------------------------------------------------------------------
/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    public static Laberinto l;
    public static Jugador jugador;
    public static float cx, cy, cz;
    public static int option;
    
    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();

        // Activar VSync
        gl.setSwapInterval(1);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Establecer el modelo de suavizado para el coloreado 
        // GL_SMOOTH interpola los colores 
        // Referencia para entenderlo mejor http://albertojaspe.net/uploads/articulos/tutorial-opengl.pdf
        gl.glShadeModel(GL.GL_SMOOTH);
        //  GL_DEPTH_TEST: Buffer de profundidad que permita la visibilidad de los graficos 3D 
        // segun las coordenadas de los pixeles (mas que nada tiene que ver con respecto
        // a sus posiciones del eje z, que da la distancia a la camara)
        // Referencia para entenderlo mejor http://opengl-esp.superforo.net/t35-tutorial-iii-9-buffer-de-profundidad-depth-y-modo-tijera-scissor
        gl.glEnable(gl.GL_DEPTH_TEST); 
        gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);
        l = new Laberinto(gl, 0.0f, 0.0f, 0f, 1f, 1f, 1, 1f, 1f, 1f);
        jugador = new Jugador(gl, 0, 0.4f, 2f, 0.03f, 0.01f, 0.01f, 1f, 1f, 1f);
        // Parametros donde se colocará la camara
        cx = 1f;
        cy = 10f;
        cz = 0f;
        // Opcion para cambiar en camara, comienza en 1 (vista desde arriba)
        option = 1;
    }
    
    // Funcion para actualizar movimientos del jugador
    public void actualizar(){
        jugador.actualizar();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        // Evita que la division que viene a continuacion genere error 
        // por el height si en caso sea de valor 0 o menor
        if (height <= 0) { 
            height = 1;
        }
        
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void render(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        GLU glu = new GLU(); 
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        l.dibujarLaberinto();
        jugador.dibujarJugador();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45f, 1f, 0.2f, 20f);
        switch(option){
            case 1:
                // Vista desde arriba
                glu.gluLookAt(cx, cy, cz, 0f, 0f, 0f, 0f, 1f, 0f);
                break;
            case 2:
                // gluLookAt para primera persona
                break;
        }
    }

    // Mostrar en pantalla
    public void display(GLAutoDrawable drawable) {
        actualizar();
        render(drawable);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void main() {
        Frame ventana = new Frame("JUEGO"); // Metodo clase main
        ventana.setSize(1000,700); // Tamaño de la ventana
        ventana.addWindowListener(new WindowAdapter(){ // Manejador de ventanas y cerrar ventana 
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    
        GLCanvas canvas = new GLCanvas(); 
        ventana.add(canvas);
        
        // Manejador de eventos canvas
        canvas.addGLEventListener(new GLRenderer());
        
        // Crea animacion
        // Crea objeto para que se ejecute 60 veces por segundo
        Animator animador = new FPSAnimator(canvas, 60);
        animador.add(canvas);
        animador.start();
        
        ventana.setVisible(true); // Visible la ventana
        
        // Manejador de pulsacion de teclas
        Keyboard keylistener = new Keyboard(canvas);
        canvas.addKeyListener(keylistener);
    }
}

