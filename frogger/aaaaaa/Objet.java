/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frogger.aaaaaa;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author chloe et marius
 */
public class Objet {

    int left;
    int right;
    int top;
    int bottom;
    int width, height,x,y;
    protected BufferedImage image;
    protected File fichier;
    private String sens;
    private final int startX, startY;


    Objet(int x, int y, int w, int h, String nomFichier, String sens) {
        left = x;
        right = x + w;
        top = y;
        bottom = y + h;
        this.sens = sens;
        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;
        fichier = new File("src/images/" + nomFichier);
        startX= x;
        startY= y;

        try {
            image = ImageIO.read(fichier);
        } catch (IOException ex) {
            Logger.getLogger(Objet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

//Pour que les objets ne se croisent pas : //
   boolean intersects(Objet other) {
        return !(this.left > other.right
                || this.right < other.left
                || this.top > other.bottom
                || this.bottom < other.top);

    }

    public void move() {
        if (this.sens.equals("droite")) {
            this.left += 10;
            this.x += 10;
            if (this.left > 650) {
                this.left = 0;
                this.x = 0;
            }
        }
        if (this.sens.equals("gauche")) {
            this.left -= 10;
            this.x -= 10;
            if (this.left < 0) {
                this.left = 700;
                this.x = 700;
            }
        }
    }

    public Rectangle getHitbox() {

        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
}


