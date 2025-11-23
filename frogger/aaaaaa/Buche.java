/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frogger.aaaaaa;

/**
 *
 * @author chloe
 */
public class Buche extends Objet {

    private String direction;
    private int vitesse;

public Buche(int x, int y, int w, int h, String nomFichier, String sens) {
    super(x, y, w, h, nomFichier, sens);
        this.direction = sens;
    this.vitesse = 10; // ou une autre valeur de vitesse selon ton jeu

    }
public String getDirection() {
    return this.direction;
}

public int getVitesse() {
    return this.vitesse;

    }
}
