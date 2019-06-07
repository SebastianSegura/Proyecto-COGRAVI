/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;

/**
 *
 * @author sebas
 */
public class Jugador {
    public float x; // posicion en x
    public float y; // posicion en y 
    public float z; // posicion en z
    public float w; // ancho
    public float h; // altura
    public float v; // profundidad
    public float r; // Color rojo
    public float g; // Color verde
    public float b; // Color azul
    public float angle;
    
    private GL gl;
    
    // Partes del personaje (todas son hechas con cubos)
    private Cubo cabeza;
    private Cubo tronco;
    private Cubo pierna1;
    private Cubo pierna2;
    private Cubo brazo1;
    private Cubo brazo2;
    
    // Para aplicar textura
    private Texture tex;

    public Jugador(GL gl, float x, float y, float z, float w, float h, float v, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.v = v;
        this.r = r;
        this.g = g;
        this.b = b;
        this.gl = gl;
        
        // Indicar objeto de tipo texture
        try{
        // Se indica la localizacion de la imagen
        tex = TextureIO.newTexture(new File("src/imagenes/jugador.png"), true);
        }catch(IOException e){
            System.err.print("No se puede cargar textura" +e);
            System.exit(1);
        }
        
        // Crear objetos de clase cubo que son las partes del personaje
        cabeza = new Cubo(gl, x, y, z, w*2.8f, h*30, w*2.0f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        tronco = new Cubo(gl, x, y, z, w*2.0f, h*26, w*1.8f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        pierna1 = new Cubo(gl, x, y, z, w*0.6f, h*24, w*1.7f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        pierna2 = new Cubo(gl, x, y, z, w*0.6f, h*24, w*1.7f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        brazo1 = new Cubo(gl, x, y, z, w*0.3f, h*24, w*1.7f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        brazo2 = new Cubo(gl, x, y, z, w*0.3f, h*24, w*1.7f, 0, r-0.1f, g-0.2f, b+0.7f, tex);
        
        // Inicio angulo de rotacion en 0
        angle = 0.0f;
    }
    
    public void dibujarJugador(){
        cabeza.dibujarCubo();
        tronco.dibujarCubo();
        pierna1.dibujarCubo();
        pierna2.dibujarCubo();
        brazo1.dibujarCubo();
        brazo2.dibujarCubo();
    }
    
    // Actualizacion de la posicion del personaje en el escenario
    // de acuerdo a sus movimientos
    public void actualizar(){
        cabeza.x = x;
        cabeza.y = y-0.22f;
        cabeza.z = z;
        tronco.x = x;
        tronco.y = y-0.32f;
        tronco.z = z;
        brazo1.x = x-w-0.01f;
        brazo1.y = y-0.34f;
        brazo1.z = z;
        brazo2.x = x+w+0.01f;
        brazo2.y = y-0.34f;
        brazo2.z = z;
        pierna1.x = x-w+0.02f;
        pierna1.y = y-h-0.4f;
        pierna1.z = z;
        pierna2.x = x+w-0.03f;
        pierna2.y = y-h-0.4f;
        pierna2.z = z;
        cabeza.setAngle((float)(-angle*(180/Math.PI))+90);
    }
    
    public void avanzar(){
        if(!this.colision(x+(float)(Math.cos(angle)*v), z+(float)(Math.sin(angle)*v))){
            x += Math.cos(angle)*v;
            z += Math.sin(angle)*v;
        }
    }
    
    public void retroceder(){
        if(!this.colision(x-(float)(Math.cos(angle)*v), z-(float)(Math.sin(angle)*v))){
            x -= Math.cos(angle)*v;
            z -= Math.sin(angle)*v;
        }
    }
    
    // Actualizacion de angulo de rotacion del personaje
    public void izquierda(){
        angle -=0.05f;
    }
    
    public void derecha(){
        angle += 0.05f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    
    // Metodo para que el personaje no atraviese las paredes
    public boolean colision(float nx, float nz){
        boolean salida = false;
        for(Cubo l: GLRenderer.l.paredesZ){
            float umbralx = w/2+l.getW()/2;
            float umbralz = w/2+0.1f;
            salida = salida||(Math.abs(nx-l.getX()) < umbralx && (Math.abs(nz-l.getZ()) < umbralz));
        }
        
        for(Cubo l: GLRenderer.l.paredesX){
            float umbralz = w/2+l.getD()/2f;
            float umbralx = w/2f+0.1f;
            salida = salida||(Math.abs(nx-l.getX()) < umbralx && (Math.abs(nz-l.getZ()) < umbralz));
        }
        return salida;
    }
}
