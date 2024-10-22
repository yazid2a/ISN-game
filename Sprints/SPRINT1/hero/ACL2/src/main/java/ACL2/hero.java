package ACL2;

public class hero {

    private int x;
    private int y;
    private int largeur;
    private int hauteur;
    
    public hero(int x, int y, int largeur, int hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void displayGrid() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (i == y && j == x) {
                    System.out.print("H ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void displayPosition() {
        System.out.println("Position du héros : (" + x + ", " + y + ")");
    }

    public void move(char direction) {
        switch (direction) {
            case 'u': // haut
                if (y > 0) y--;
                break;
            case 'd': // bas
                if (y < hauteur - 1) y++;
                break;
            case 'l': // gauche
                if (x > 0) x--;
                break;
            case 'r': // droite
                if (x < largeur - 1) x++;
                break;
            default:
                System.out.println("Direction non valide.");
        }
    }
}
