package Monstre;

import entity.Entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class monster extends Entity {
    private BufferedImage slimeImage;
    private boolean isAlive = true;

    public monster(int x, int y, GamePanel gp) {
        super(gp);  // Appel du constructeur parent Entity avec GamePanel
        this.worldX = x;
        this.worldY = y;
        setSlimeImage();
    }


    // Charger l'image du monstre
    private void setSlimeImage() {
        try {
            slimeImage = ImageIO.read(getClass().getResourceAsStream("/res/monster/slime.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode update pour le monstre (par exemple pour gérer le déplacement ou d'autres actions)
    public void update() {
        if (isAlive) {
            // Exemple de mouvement basique : déplacement horizontal
            worldX += 2; // Déplacer le monstre de 2 pixels à chaque mise à jour
            if (worldX > 800) { // Si le monstre dépasse une certaine position, on le ramène à la position initiale
                worldX = 0;
            }
        }
    }

    // Méthode pour dessiner le monstre
    public void draw(Graphics2D g2) {
        if (isAlive) {
            g2.drawImage(slimeImage, worldX, worldY, null);
        }
    }

    // Méthode pour rendre le monstre mort
    public void setDead() {
        isAlive = false;
    }

    // Vérifier si le monstre est encore en vie
    public boolean isAlive() {
        return isAlive;
    }
}
