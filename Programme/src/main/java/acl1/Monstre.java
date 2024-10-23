package acl1;

import java.util.Random;

public class Monstre {
    private int x, y;
    private Random random = new Random();

    public Monstre(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void deplacerAleatoire(int[][] labyrinthe) {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: // haut
                if (x > 0 && labyrinthe[x - 1][y] != 1) {
                    x--;
                }
                break;
            case 1: // bas
                if (x < labyrinthe.length - 1 && labyrinthe[x + 1][y] != 1) {
                    x++;
                }
                break;
            case 2: // gauche
                if (y > 0 && labyrinthe[x][y - 1] != 1) {
                    y--;
                }
                break;
            case 3: // droite
                if (y < labyrinthe[0].length - 1 && labyrinthe[x][y + 1] != 1) {
                    y++;
                }
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
