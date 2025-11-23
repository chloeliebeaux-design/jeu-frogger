/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frogger.aaaaaa;

import java.awt.Rectangle;

/**
 *
 * @author chloe et marius
 */
public class Perso extends Objet {

    public Perso(int x, int y, int w, int h, String nomFichier) {
        super(x, y, w, h, nomFichier,"");
        
    }
public void resetPosition() {
    this.x = 380;
    this.y = 650;
    this.left = 380;
    this.bottom = 650;
}
}
