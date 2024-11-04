package game.programme2;

import java.awt.*;
import java.util.Random;
public class labyrinthe {
	private final int lignes;
    private final int colonnes;
    private final int[][] labyrinthe;
    private final Random aleatoire = new Random();
    private final int cellSize = 20; // Taille d'une cellule pour l'affichage

    public labyrinthe(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.labyrinthe = new int[lignes][colonnes];
        genererLabyrinthe(0, 0);
    }

    private void genererLabyrinthe(int x, int y) {
        int[] directions = new int[]{0, 1, 2, 3}; // 0: haut, 1: droite, 2: bas, 3: gauche
        melangerTableau(directions);

        for (int i = 0; i < directions.length; i++) {
            int nx = x, ny = y;

            switch (directions[i]) {
                case 0: ny = y - 2; break;
                case 1: nx = x + 2; break;
                case 2: ny = y + 2; break;
                case 3: nx = x - 2; break;
            }

            if (dansLesLimites(nx, ny) && labyrinthe[nx][ny] == 0) {
                labyrinthe[nx][ny] = 1;
                labyrinthe[(x + nx) / 2][(y + ny) / 2] = 1;
                genererLabyrinthe(nx, ny);
            }
        }
    }

    private void melangerTableau(int[] tableau) {
        for (int i = tableau.length - 1; i > 0; i--) {
            int index = aleatoire.nextInt(i + 1);
            int temp = tableau[index];
            tableau[index] = tableau[i];
            tableau[i] = temp;
        }
    }

    private boolean dansLesLimites(int x, int y) {
        return x >= 0 && x < lignes && y >= 0 && y < colonnes;
    }

    public int[][] getLabyrinthe() {
        return labyrinthe;
    }
    
    public Point findStartingPosition() {
    	for(int i=0;i<lignes;i++) {
    		for (int j=0;j<colonnes;j++) {
    			if (labyrinthe[i][j]==1) {
    				return new Point(j,i);
    			}
    		}
    	}
    	return new Point(1,1);//Par défaut si on ne trouve aucun chemin
    }

    // Méthode pour dessiner le labyrinthe
    public void render(Graphics g) {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (labyrinthe[i][j] == 0) { // Dessine les murs
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}