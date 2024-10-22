package ACL2;

import java.util.Random;

public class Monstre {

    private int x;
    private int y;
    private int largeur;
    private int hauteur;
    private Random random = new Random();

    public Monstre(int startX, int startY, int largeur, int hauteur) {
        this.x = startX;
        this.y = startY;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void deplacerAleatoire() {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: // haut
                if (y > 0) y--;
                break;
            case 1: // bas
                if (y < hauteur - 1) y++;
                break;
            case 2: // gauche
                if (x > 0) x--;
                break;
            case 3: // droite
                if (x < largeur - 1) x++;
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
