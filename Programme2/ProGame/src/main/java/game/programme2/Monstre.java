package game.programme2;

import java.awt.*;

import java.util.Random;



public class Monstre extends Rectangle {

    private static final long serialVersionUID = 1L;



    private final int speed = 30; // Vitesse de déplacement

    private final int moveDelay = 20; // Délai entre les mouvements pour ralentir le monstre

    private int moveCounter = 0; // Compteur pour gérer le délai



    private labyrinthe labyrinthe;

    private Random random;



    public Monstre(int x, int y, labyrinthe labyrinthe) {

        this.x = x;

        this.y = y;

        this.labyrinthe = labyrinthe;



        setBounds(x, y, 33, 33); // Taille identique à celle du héros

        random = new Random();

    }



    public void tick() {

        moveCounter++;

        if (moveCounter >= moveDelay) { // Le monstre se déplace seulement lorsque le compteur atteint le délai

            moveCounter = 0; // Réinitialiser le compteur

            moveInRandomDirection(); // Déplacez le monstre dans une direction aléatoire

        }

    }



    private void moveInRandomDirection() {

        int newX = x, newY = y;



        // Choisir une direction aléatoire

        int direction = random.nextInt(4);

        switch (direction) {

            case 0: // haut

                newY -= speed;

                break;

            case 1: // bas

                newY += speed;

                break;

            case 2: // gauche

                newX -= speed;

                break;

            case 3: // droite

                newX += speed;

                break;

        }



        // Si le monstre peut se déplacer dans la nouvelle direction, il y va

        if (canMoveTo(newX, newY)) {

            x = newX;

            y = newY;

        } else {

            // Si bloqué, choisir une nouvelle direction

            moveInRandomDirection(); // Essaye de bouger à nouveau dans une direction différente

        }



        setBounds(x, y, 33, 33); // Mettre à jour la position du rectangle

    }



    private boolean canMoveTo(int newX, int newY) {

        int mazeX = newX / speed;

        int mazeY = newY / speed;

        // Vérifie si les coordonnées sont dans les limites du labyrinthe et qu'il n'y a pas de mur

        return mazeX >= 0 && mazeX < labyrinthe.getLabyrinthe()[0].length &&

               mazeY >= 0 && mazeY < labyrinthe.getLabyrinthe().length &&

               labyrinthe.getLabyrinthe()[mazeY][mazeX] == 1;

    }



    public void render(Graphics g) {

        g.setColor(Color.red); // Couleur du monstre

        g.fillOval(x, y, 30, 30); // Taille identique à celle du héros

    }

}