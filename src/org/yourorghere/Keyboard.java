/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author sebas
 */
class Keyboard implements KeyListener{
    private GLCanvas canvas;
    public boolean[] keybuffer;
    
    GLU glu = new GLU();
    
    public Keyboard(GLCanvas canvas){
        this.canvas = canvas;
        // 256 caracteres del codigo ASCII para la 
        // interpretacion de caracteres del alfabeto y simbolos
        keybuffer = new boolean[256];
    }

    public void keyTyped(KeyEvent e) {
        if(keybuffer['w']){
            GLRenderer.jugador.avanzar();
        }
        if(keybuffer['s']){
            GLRenderer.jugador.retroceder();
        }
        if(keybuffer['a']){
            GLRenderer.jugador.izquierda();
        }
        if(keybuffer['d']){
            GLRenderer.jugador.derecha();
        }
        // Camara perspectiva
        if(e.getKeyChar()=='p'){
            GLRenderer.option=1;
        }
        // Camara primera persona
        if(e.getKeyChar()=='o'){
            GLRenderer.option=2;
        }
    }

    public void keyPressed(KeyEvent e) {
        keybuffer[e.getKeyChar()]=true;
    }

    public void keyReleased(KeyEvent e) {
        keybuffer[e.getKeyChar()]=false;
    }
    
}
