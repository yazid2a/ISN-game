package Objects;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Heart {
    private int maxLife;
    private int currentLife;
    private BufferedImage fullHeart, halfHeart, emptyHeart;

    public OBJ_Heart(int maxLife) {
        this.maxLife = maxLife;
        this.currentLife = maxLife;
        loadHeartImages();
    }

    private void loadHeartImages() {
        try {
            fullHeart = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_full.png"));
            halfHeart = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_half.png"));
            emptyHeart = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loseLife() {
        if (currentLife > 0) {
            currentLife--;
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        int hearts = maxLife / 2;
        for (int i = 0; i < hearts; i++) {
            if (i < currentLife / 2) {
                g2.drawImage(fullHeart, x + i * 40, y, null);
            } else if (i < currentLife / 2 + 1 && currentLife % 2 != 0) {
                g2.drawImage(halfHeart, x + i * 40, y, null);
            } else {
                g2.drawImage(emptyHeart, x + i * 40, y, null);
            }
        }
    }

    public int getCurrentLife() {
        return currentLife;
    }
}
