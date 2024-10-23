package Labyrinthe;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Labyrinthe labyrinthe;
    private final int tailleCase = 32; // Taille d'une case du labyrinthe
    private int[][] donneesLabyrinthe;

    public GamePanel() {
        this.labyrinthe = new Labyrinthe(17, 17); 
        this.donneesLabyrinthe = labyrinthe.getLabyrinthe();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerLabyrinthe(g);
    }

    
    private void dessinerLabyrinthe(Graphics g) {
        for (int i = 0; i < donneesLabyrinthe.length; i++) {
            for (int j = 0; j < donneesLabyrinthe[0].length; j++) {
                if (donneesLabyrinthe[i][j] == 0) {
                    g.setColor(Color.BLACK); //  les murs
                    g.fillRect(i * tailleCase, j * tailleCase, tailleCase, tailleCase);
                } else {
                    g.setColor(Color.WHITE); //  les chemins
                    g.fillRect(i * tailleCase, j * tailleCase, tailleCase, tailleCase);
                }
            }
        }
    }
}
