package Labyrinthe;

import javax.swing.JFrame;

public class LabyrintheJeu {
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Jeu de Labyrinthe");
        GamePanel panneauDeJeu = new GamePanel();

        fenetre.add(panneauDeJeu);
        fenetre.setSize(500, 500); 
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        fenetre.setVisible(true); 
    }
}

