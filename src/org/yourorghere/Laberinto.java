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
import java.util.ArrayList;
import javax.media.opengl.GL;

/**
 *
 * @author sebas
 */
public class Laberinto {
    // Posicion
    public float x; // en x
    public float y; // en y
    public float z; // en z
    // Dimensiones
    private float w; // ancho
    private float h; // alto
    private float d; // profundida
    // Colores
    private float r; // rojo
    private float g; // verde
    private float b; // azul
    private float angle; // angulo de rotacion
    private GL gl;
    public Cubo piso, p1, p2, p3, p4, p21, p41;
    public Cubo pz1, pz2, pz3, pz4, pz5, pz6, pz7;
    public Cubo p1z, p2z, p3z, p4z, p5z, p6z, p7z, p8z;
    public Cubo px1, px2, px3, px4, px5, px6;
    public Cubo p1x, p2x, p3x, p4x, p5x, p6x, p7x, p8x;
    public ArrayList<Cubo> paredesX;
    public ArrayList<Cubo> paredesZ;
    // Textura
    private Texture floor, paredes, cerramiento;

    public Laberinto(GL gl, float x, float y, float z, float w, float h, float d,
            float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.r = r;
        this.g = g;
        this.b = b;
        this.gl = gl;
        
        
        try{
            floor = TextureIO.newTexture(new File("src/imagenes/piso2.jpg"), true);
            paredes = TextureIO.newTexture(new File("src/imagenes/bloque1.jpg"), true);
            cerramiento = TextureIO.newTexture(new File("src/imagenes/bloque2.jpg"), true);
        }catch(IOException e){
            System.err.print("No se puede cargar textura" + e);
            System.exit(1);
        }
        
      
        piso = new Cubo(gl, x, y, z, w*4, h/5, d*4, 0, 1f, 1f, 1f, floor);
        p1 = new Cubo(gl, x-w*2f, y+h/2, z, w/20, h, d*4, 0, 1f, 1f, 1f, cerramiento); 
        p2 = new Cubo(gl, x-0.5f, y+h/2, z-d*2, w*3, h, d/20, 0, 1f, 1f, 1f, cerramiento);
        p3 = new Cubo(gl, x+w*2, y+h/2, z, w/20, h, d*4, 0, 1f, 1f, 1f, cerramiento);
        p4 = new Cubo(gl, x-1.2f, y+h/2, z+d*2, w*1.6f, h, d/20, 0, 1f, 1f, 1f, cerramiento);
        p41 = new Cubo(gl, x+1.2f, y+h/2, z+d*2, w*1.6f, h, d/20, 0, 1f, 1f, 1f, cerramiento);
        
        // Paredes laterales parte superior
        pz1 = new Cubo(gl, x-1.3f, y+h/2, z+1.3f, w*1.4f, h, d/20, 0, 1, 1f, 1, paredes);
        pz2 = new Cubo(gl, x-1.3f, y+h/2, z+1f, w/2, h, d/20, 0, 1, 1f, 1, paredes);
        pz3 = new Cubo(gl, x-1.3f, y+h/2, z+0.5f, w*0.6f, h, d/20, 0, 1, 1f, 1, paredes);
        pz4 = new Cubo(gl, x-1.1f, y+h/2, z, w+0.2f, h, d/20, 0, 1, 1f, 1, paredes);
        pz5 = new Cubo(gl, x-0.75f, y+h/2, z-0.5f, w+0.5f, h, d/20, 0, 1, 1f, 1, paredes);
        pz6 = new Cubo(gl, x-0.6f, y+h/2, z-0.8f, w*0.6f, h, d/20, 0, 1, 1f, 1, paredes);
        pz7 = new Cubo(gl, x-1.2f, y+h/2, z-1f, w*0.6f, h, d/20, 0, 1, 1f, 0, paredes);
        
        // Paredes laterales parte inferior
        p1z = new Cubo(gl, x+1.2f, y+h/2, z+1.5f, w+0.5f, h, d/20, 0, 1, 1f, 1, paredes);
        p2z = new Cubo(gl, x+1.5f, y+h/2, z+1f, w/2f, h, d/20, 0, 1, 1f, 1, paredes);
        p3z = new Cubo(gl, x+0.75f, y+h/2, z+0.25f, w*1.5f, h, d/20, 0, 1, 1f, 1, paredes);
        p4z = new Cubo(gl, x+1.6f, y+h/2, z-0.25f, w*0.8f, h, d/20, 0, 1, 1f, 1, paredes);
        p5z = new Cubo(gl, x+0.5f, y+h/2, z-0.25f, w*0.5f, h, d/20, 0, 1, 1f, 1, paredes);
        p6z = new Cubo(gl, x+0.5f, y+h/2, z-1f, w*1.5f, h, d/20, 0, 1, 1f, 1, paredes);
        p7z = new Cubo(gl, x+0.2f, y+h/2, z-1.5f, w*1.5f, h, d/20, 0, 1, 1f, 1, paredes);
        p8z = new Cubo(gl, x+1.5f, y+h/2, z-1.5f, w/2f, h, d/20, 0, 1, 1f, 1, paredes);
        
        // Paredes horizontales parte superior
        px1 = new Cubo(gl, x-0.6f, y+h/2, z+0.6f, w/20, h, d*1.2f, 0, 1f, 1f, 1f, paredes);
        px2 = new Cubo(gl, x-1.6f, y+h/2, z+0.4f, w/20, h, d*1.2f, 0, 1f, 1f, 1f, paredes);
        px3 = new Cubo(gl, x-1.5f, y+h/2, z-0.8f, w/20, h, d*0.6f, 0, 1f, 1f, 1f, paredes);
        px4 = new Cubo(gl, x-0.9f, y+h/2, z-1.4f, w/20, h, d+0.2f, 0, 1f, 1f, 1f, paredes);
        px5 = new Cubo(gl, x, y+h/2, z+0.5f, w/20, h, d*1, 0, 1f, 1f, 1f, paredes);
        
        // Paredes horizontales parte inferior
        p1x = new Cubo(gl, x+0.75f, y+h/2, z+1.1f, w/20, h, d*1.1f, 0, 1f, 1f, 1f, paredes);
        p2x = new Cubo(gl, x+1.4f, y+h/2, z+0.8f, w/20, h, d/2, 0, 1f, 1f, 1f, paredes);
        p3x = new Cubo(gl, x+0.3f, y+h/2, z-0.25f, w/20, h, d/2, 0, 1f, 1f, 1f, paredes);
        p4x = new Cubo(gl, x+1f, y+h/2, z-0.6f, w/20, h, d, 0, 1f, 1f, 1f, paredes);
        p5x = new Cubo(gl, x-0.1f, y+h/2, z-1.6f, w/20, h, d*0.8f, 0, 1f, 1f, 1f, paredes);
        p5x = new Cubo(gl, x+1.8f, y+h/2, z-1.6f, w/20, h, d*0.8f, 0, 1f, 1f, 1f, paredes);
        
        // Agregar paredes verticales a un arraylist
        paredesX = new ArrayList<Cubo>();
        paredesX.add(px1);
        paredesX.add(px2);
        paredesX.add(px3);
        paredesX.add(px4);
        paredesX.add(px5);
        paredesX.add(p1x);
        paredesX.add(p2x);
        paredesX.add(p3x);
        paredesX.add(p4x);
        paredesX.add(p5x);
        paredesX.add(p1); // Pared limite superior
        paredesX.add(p3); // Pared limite inferior
        
        // Agregar paredes horizontales a un arraylist
        paredesZ = new ArrayList<Cubo>();
        paredesZ.add(pz1);
        paredesZ.add(pz2);
        paredesZ.add(pz3);
        paredesZ.add(pz4);
        paredesZ.add(pz5);
        paredesZ.add(pz6);
        paredesZ.add(pz7);
        paredesZ.add(p1z);
        paredesZ.add(p2z);
        paredesZ.add(p3z);
        paredesZ.add(p4z);
        paredesZ.add(p5z);
        paredesZ.add(p6z);
        paredesZ.add(p7z);
        paredesZ.add(p8z);
        // Entrada y salida del laberinto
        paredesZ.add(p2);
        paredesZ.add(p4);
        paredesZ.add(p41);
    }
    
    public void dibujarLaberinto(){
        // Dibujar paredes verticales
        for(Cubo v: paredesX){
            v.dibujarCubo();
        }
        // Dibujar paredes horizontales
        for(Cubo v: paredesZ){
            v.dibujarCubo();
        }
        piso.dibujarCubo();
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public float getZ(){
        return z;
    }

    public float getW(){
        return w;
    }
    
}
