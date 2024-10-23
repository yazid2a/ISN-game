
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int largeur = 5;
        int hauteur = 5;

        hero hero = new hero(largeur / 2, hauteur / 2, largeur, hauteur);

        while (true) {
            hero.displayGrid();
            hero.displayPosition();

            System.out.println("Utilise u (haut), d (bas), l (gauche), r (droite) pour déplacer le héros.");
            char direction = scanner.next().charAt(0);

            hero.move(direction);
        }
    }
}

