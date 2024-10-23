package acl1;

import java.util.Scanner;

public class LabyrintheJeu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] labyrinthe = {
            {1, 1, 1, 1, 1,0,0,0,0,1},
            {1, 0, 0, 0, 0,0,0,0,1},
            {1, 0, 1, 0, 0,0,0,0,1},
            {1, 0, 0, 0, 0,0,0,0,1},
            {1, 1, 1, 1, 0,0,0,0,1},
            
        };

        Hero hero = new Hero(1, 1, 100);
        Monstre monstre = new Monstre(3, 3);

        while (hero.getHealth() > 0) {
            // Affiche l'état actuel du labyrinthe
            afficherLabyrinthe(labyrinthe, hero, monstre);

            // Demande à l'utilisateur de déplacer le héros
            System.out.println("Déplacez le héros avec w (haut), s (bas), a (gauche), d (droite):");
            char direction = scanner.next().charAt(0);
            switch (direction) {
                case 'w':
                    hero.deplacer(-1, 0, labyrinthe);
                    break;
                case 's':
                    hero.deplacer(1, 0, labyrinthe);
                    break;
                case 'a':
                    hero.deplacer(0, -1, labyrinthe);
                    break;
                case 'd':
                    hero.deplacer(0, 1, labyrinthe);
                    break;
                default:
                    System.out.println("Direction non valide.");
            }

            // Le monstre se déplace
            monstre.deplacerAleatoire(labyrinthe);

            // Vérifie la collision
            if (hero.detecterCollision(monstre)) {
                System.out.println("Collision! Le héros perd des points de vie.");
                hero.perdreVie();
            }

            // Vérifie si le jeu est terminé
            if (hero.getHealth() <= 0) {
                System.out.println("Game Over!");
                break;
            }
        }

        scanner.close();
    }

    private static void afficherLabyrinthe(int[][] labyrinthe, Hero hero, Monstre monstre) {
        for (int i = 0; i < labyrinthe.length; i++) {
            for (int j = 0; j < labyrinthe[i].length; j++) {
                if (i == hero.getX() && j == hero.getY()) {
                    System.out.print("H ");
                } else if (i == monstre.getX() && j == monstre.getY()) {
                    System.out.print("M ");
                } else {
                    System.out.print((labyrinthe[i][j] == 1 ? "#" : ".") + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Santé du héros: " + hero.getHealth());
    }
}
