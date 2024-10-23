package acl1;

import java.util.Random;

public class Labyrinthe {
    private final int lignes;
    private final int colonnes;
    private final int[][] labyrinthe;
    private final Random aleatoire = new Random();

    public Labyrinthe(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.labyrinthe = new int[lignes][colonnes];
        genererLabyrinthe(0, 0); 
    }

    
    private void genererLabyrinthe(int x, int y) {
        int[] directions = new int[]{0, 1, 2, 3}; // 0: haut, 1: droite, 2: bas, 3: gauche
        melangerTableau(directions); // Mélange les directions pour un labyrinthe aléatoire

        for (int i = 0; i < directions.length; i++) {
            int nx = x, ny = y;

            // Vérifie les directions
            switch (directions[i]) {
                case 0: ny = y - 2; break; 
                case 1: nx = x + 2; break; 
                case 2: ny = y + 2; break; 
                case 3: nx = x - 2; break; 
            }

            // Vérifie si la nouvelle position est dans les limites et non visitée
            if (dansLesLimites(nx, ny) && labyrinthe[nx][ny] == 0) {
                labyrinthe[nx][ny] = 1; // 
                labyrinthe[(x + nx) / 2][(y + ny) / 2] = 1; 
                genererLabyrinthe(nx, ny); 
            }
        }
    }

    // Mélange les directions pour les rendre aléatoires
    private void melangerTableau(int[] tableau) {
        for (int i = tableau.length - 1; i > 0; i--) {
            int index = aleatoire.nextInt(i + 1);
            int temp = tableau[index];
            tableau[index] = tableau[i];
            tableau[i] = temp;
        }
    }

    // Vérifie si les coordonnées sont dans les limites du labyrinthe
    private boolean dansLesLimites(int x, int y) {
        return x >= 0 && x < lignes && y >= 0 && y < colonnes;
    }
    public int[][] getLabyrinthe() {
        return labyrinthe;
    }
    public void afficherLabyrinthe() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(labyrinthe[i][j] == 1 ? " " : "#");
            }
            System.out.println();
        }
    }
}
