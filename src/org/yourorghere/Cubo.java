/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;

/**
 *
 * @author sebas
 */
public class Cubo {
    private GL gl;
    // Posicion
    public float x; // en x
    public float y; // en y
    public float z; // en z
    // Dimensiones
    private float w; // ancho
    private float h; // alto
    private float d; // profundidad
    // Colores
    private float r; // rojo
    private float g; // verde
    private float b; // azul
    private float angle; // angulo de rotacion
    // Textura
    private Texture tex;

    public Cubo(GL gl, float x, float y, float z, float w, float h, float d, float r, float g,
            float b, float angle, Texture tex) {
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.r = r;
        this.g = g;
        this.b = b;
        this.angle = angle;
        this.tex = tex;
    }
    
    public void dibujarCubo(){
        gl.glPushMatrix();
            gl.glTranslatef(x, y, z); // Traslada el eje de puntos al origen
//            gl.glRotatef(angle, 0, 1, 0); 
            gl.glTranslatef(-x, -y, -z); // Permite volver al origen
            
            //---------------------------------------
            tex.enable(); // Habilitar textura
               tex.bind(); // Pegar textura
               gl.glNormal3f(0, 0, 1); 
           
            // Cara frontal
            gl.glBegin(GL.GL_QUADS); 
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,0); // Agregar textura
            gl.glVertex3f(x-w/2,y+h/2,z-d/2); // Primer vertice
            gl.glTexCoord2f(1,0);// Agregar textura
            gl.glVertex3f(x-w/2,y-h/2,z-d/2); // Segundo vertice
            gl.glTexCoord2f(1,1); // Agregar textura
            gl.glVertex3f(x+w/2,y-h/2,z-d/2);// Tercer vertice
            gl.glTexCoord2f(0,1); // Agregar textura
            gl.glVertex3f(x+w/2,y+h/2,z-d/2); // Cuarto vertice
            gl.glEnd();
            tex.disable();
            //---------------------------------------
            
            tex.enable();
               tex.bind(); 
               gl.glNormal3f(0, 0, -1); 
           // Cara trasera
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(x-w/2,y+h/2,z+d/2);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(x-w/2,y-h/2,z+d/2);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(x+w/2,y-h/2,z+d/2);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(x+w/2,y+h/2,z+d/2);
            gl.glEnd();
            tex.disable();
            //---------------------------------------
            
            tex.enable(); 
               tex.bind(); 
               gl.glNormal3f(-1, 0, 0); 
            // Cara izquierda
            gl.glBegin(GL.GL_QUADS); 
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(x-w/2,y+h/2,z-d/2);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(x-w/2,y-h/2,z-d/2);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(x-w/2,y-h/2,z+d/2);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(x-w/2,y+h/2,z+d/2);
            gl.glEnd();
            tex.disable();
            //---------------------------------------
            
            tex.enable(); 
               tex.bind(); 
               gl.glNormal3f(1, 0, 0); 
            // Cara derecha
            gl.glBegin(GL.GL_QUADS); 
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(x+w/2,y+h/2,z-d/2);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(x+w/2,y-h/2,z-d/2); 
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(x+w/2,y-h/2,z+d/2);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(x+w/2,y+h/2,z+d/2);
            gl.glEnd();
            tex.disable();
            //---------------------------------------
            
            tex.enable(); 
               tex.bind(); 
               gl.glNormal3f(0, 1, 0); 
            // Cara arriba
            gl.glBegin(GL.GL_QUADS);   
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(x-w/2,y+h/2,z-d/2);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(x+w/2,y+h/2,z-d/2);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(x+w/2,y+h/2,z+d/2);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(x-w/2,y+h/2,z+d/2);
            gl.glEnd();
            tex.disable();
            //---------------------------------------
            
            tex.enable(); 
               tex.bind(); 
               gl.glNormal3f(0, -1, 0); 
            // Cara abajo
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(r,g,b);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(x-w/2,y-h/2,z-d/2);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(x+w/2,y+h/2,z-d/2);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(x+w/2,y-h/2,z+d/2);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(x-w/2,y-h/2,z+d/2);
            gl.glEnd();
            tex.disable();
            gl.glPopMatrix();
    }
   
    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getD() {
       return d;
    }
    
    
}
