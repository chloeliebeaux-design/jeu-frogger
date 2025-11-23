/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frogger.aaaaaa;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author chloe et marius
 */
public class IHM extends javax.swing.JFrame {

    private Perso perso = new Perso(380, 650, 50, 50, "perso.png");
    private Voiture voiture1droite = new Voiture(0, 80, 120, 54, "voiture1droite.png", "droite");
    private Voiture voiture2droite = new Voiture(350, 80, 120, 47, "voiture2droite.png", "droite");
    private Voiture voiture1gauche = new Voiture(700, 140, 120, 46, "voiture1gauche.jpg", "gauche");
    private Voiture voiture2gauche = new Voiture(350, 140, 120, 54, "voiture2gauche.jpg", "gauche");
    private Voiture voiture3droite = new Voiture(350, 350, 120, 47, "voiture3droite.jpg", "droite");
    private Voiture voiture4droite = new Voiture(0, 350, 120, 54, "voiture1droite.png", "droite");
    private Voiture voiture3gauche = new Voiture(700, 410, 120, 48, "voiture3gauche.png", "gauche");
    private Voiture voiture4gauche = new Voiture(350, 410, 120, 54, "voiture2gauche.jpg", "gauche");
    private Buche buche1 = new Buche(0, 220, 177, 55, "buche2.jpg", "droite");
    private Buche buche2 = new Buche(0, 275, 177, 55, "buche2.jpg", "gauche");
    private Buche buche3 = new Buche(350, 220, 177, 55, "buche2.jpg", "droite");
    private Buche buche4 = new Buche(350, 275, 177, 55, "buche2.jpg", "gauche");
    private Buche buche5 = new Buche(0, 485, 177, 55, "buche2.jpg", "droite");
    private Buche buche6 = new Buche(0, 540, 177, 55, "buche2.jpg", "gauche");
    private Buche buche7 = new Buche(350, 485, 177, 55, "buche2.jpg", "droite");
    private Buche buche8 = new Buche(350, 540, 177, 55, "buche2.jpg", "gauche");
    private Objet map = new Objet(0, 0, 0, 0, "maps.png", "");

    private ArrayList<Voiture> listeVoitures = new ArrayList<>();
    private ArrayList<Buche> listeBuches = new ArrayList<>();

    private boolean drawHitboxes = true;
    private long startTime;
    private long endTime;   
    private int voitureTimer;

    /**
     * Creates new form IHM
     */
    public IHM() {
        initComponents();
        listeBuches.add(buche1);
        listeBuches.add(buche2);
        listeBuches.add(buche3);
        listeBuches.add(buche4);
        listeBuches.add(buche5);
        listeBuches.add(buche6);
        listeBuches.add(buche7);
        listeBuches.add(buche8);
        listeVoitures.add(voiture1droite);
        listeVoitures.add(voiture2droite);
        listeVoitures.add(voiture3droite);
        listeVoitures.add(voiture4droite);
        listeVoitures.add(voiture1gauche);
        listeVoitures.add(voiture2gauche);
        listeVoitures.add(voiture3gauche);
        listeVoitures.add(voiture4gauche);
        setBounds(0, 0, 800, 825);
        
        String[] options = {"Facile", "Moyen", "Difficile"};
String niveau = (String) JOptionPane.showInputDialog(
        null,
        "Choisissez un niveau de difficulté :",
        "Niveau",
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

if (niveau == null) {
    // Si pas de niveau selectionné 
    niveau = "Moyen"; 
}

switch (niveau) {
    case "Facile":
        voitureTimer = 160;
        break;
    case "Moyen":
        voitureTimer = 120;
        break;
    case "Difficile":
        voitureTimer = 90;
        break;
    default:
        voitureTimer = 120;
        break;
}
        // Déplacement perso
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                switch (key) {
                    case KeyEvent.VK_UP:
                        perso.bottom -= 20;
                        perso.y -= 20;
                        break;
                    case KeyEvent.VK_DOWN:
                        perso.bottom += 20;
                        perso.y += 20;
                        break;
                    case KeyEvent.VK_LEFT:
                        perso.left -= 20;
                        perso.x -= 20;
                        break;

                    case KeyEvent.VK_RIGHT:
                        perso.left += 20;
                        perso.x += 20;
                        break;

                }
                jPanel1.repaint();

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer t = new Timer(voitureTimer, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeVoitures.size(); i++) {
                    Voiture v = listeVoitures.get(i);
                    v.move();
                }
                checkCollision();
                checkVictoire();
                jPanel1.repaint();
            }

        });

        Timer t2 = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeBuches.size(); i++) {
                    Buche b = listeBuches.get(i);
                    b.move();
                }
        deplacerPersoAvecBuche(); 
        checkEau();
                jPanel1.repaint();
            }
        });
        startTime = System.currentTimeMillis();
        t.start();
        t2.start();

    }



    private boolean collisionDetectee = false;

private void checkCollision() {
    for (Voiture v : listeVoitures) {
        if (perso.getHitbox().intersects(v.getHitbox())) {
            perso.resetPosition();
            JOptionPane.showMessageDialog(this, "Perdu! Vous avez touché une voiture.", "Game Over", JOptionPane.WARNING_MESSAGE);
            break; 
        }
    }
}
private void deplacerPersoAvecBuche() {
    for (Buche b : listeBuches) {
        if (perso.getHitbox().intersects(b.getHitbox())) { 
            if ("droite".equals(b.getDirection())) {
                perso.x += b.getVitesse();    // Le perso suit la bûche
                perso.left += b.getVitesse(); 
            } else if ("gauche".equals(b.getDirection())) {
                perso.x -= b.getVitesse();    // Le perso suit la bûche dans l'autre sens
                perso.left -= b.getVitesse(); 
            }
        }
    }
}
private void checkEau() {
    boolean estDansZoneEau1 = perso.y >= 200 && perso.y <= 300;
    boolean estDansZoneEau2 = perso.y >= 470 && perso.y <= 600;
    boolean estDansEau = estDansZoneEau1 || estDansZoneEau2;
    boolean surBuche = false;

    //Vérifier buche
    for (Buche b : listeBuches) {
        if (perso.getHitbox().intersects(b.getHitbox())) {
            surBuche = true;
            break;
        }
    }
    
    if (estDansEau && !surBuche) {
        perso.resetPosition();
        JOptionPane.showMessageDialog(this, "Perdu! Vous êtes tombé dans l'eau.", "Game Over", JOptionPane.WARNING_MESSAGE);
    }
}
private void checkVictoire() {
    if (perso.y <= 20) { 
        endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime; 
        double seconds = elapsedTime / 1000.0; //Convertir en secondes

        JOptionPane.showMessageDialog(this, 
            "Félicitations ! Vous avez gagné !\nTemps réalisé : " + seconds + " secondes", 
            "Victoire", 
            JOptionPane.INFORMATION_MESSAGE);

        perso.resetPosition(); 
        startTime = System.currentTimeMillis(); 
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel()
        {

            public void paintComponent(Graphics g)
            {
                g.drawImage(map.getImage(), map.left, map.bottom, null);
                for (int i = 0; i < listeVoitures.size(); i++) {
                    g.drawImage(listeVoitures.get(i).getImage(), listeVoitures.get(i).left, listeVoitures.get(i).y, null);
                    if(drawHitboxes){
                        Rectangle hitbox = listeVoitures.get(i).getHitbox();
                        g.drawRect((int)hitbox.getX(),(int) hitbox.getY(),(int) hitbox.getWidth(),(int) hitbox.getHeight());
                    }
                }
                for (int i = 0; i < listeBuches.size(); i++) {
                    g.drawImage(listeBuches.get(i).getImage(), listeBuches.get(i).left, listeBuches.get(i).y, null);
                    if(drawHitboxes){
                        Rectangle hitbox = listeBuches.get(i).getHitbox();
                        g.drawRect((int)hitbox.getX(),(int) hitbox.getY(),(int) hitbox.getWidth(),(int) hitbox.getHeight());
                    }
                }
                g.drawImage(perso.getImage(), perso.left, perso.y, null);
                if(drawHitboxes){
                    Rectangle hitbox = perso.getHitbox();
                    g.drawRect((int)hitbox.getX(),(int) hitbox.getY(),(int) hitbox.getWidth(),(int) hitbox.getHeight());
                }
            }

        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 1300));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
