package game.programme2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class hero extends Rectangle {
    private static final long serialVersionUID = 1L;
    
    public boolean right, left, up, down;
    private final int speed = 30;
    private labyrinthe labyrinthe;
    private int vie = 100;

    private BufferedImage upImage, downImage, leftImage, rightImage;
    private String direction = "down"; // Direction par défaut

    public hero(int x, int y, labyrinthe labyrinthe) {
        this.x = x;
        this.y = y;
        this.labyrinthe = labyrinthe;
        setBounds(x, y, 33, 33);
        loadHeroImages();
    }

    private void loadHeroImages() {
        try {
            upImage = ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
            downImage = ImageIO.read(getClass().getResourceAsStream("/player/down.png"));
            leftImage = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
            rightImage = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (right && canMoveTo(x + speed, y)) {
            x += speed;
            direction = "right";
            resetMoveFlags();
        }
        if (left && canMoveTo(x - speed, y)) {
            x -= speed;
            direction = "left";
            resetMoveFlags();
        }
        if (up && canMoveTo(x, y - speed)) {
            y -= speed;
            direction = "up";
            resetMoveFlags();
        }
        if (down && canMoveTo(x, y + speed)) {
            y += speed;
            direction = "down";
            resetMoveFlags();
        }
    }

    public void decreaseLife(int amount) {
        vie = Math.max(vie - amount, 0);
    }

    public int getVie() {
        return vie;
    }

    private boolean canMoveTo(int newX, int newY) {
        int mazeX = newX / speed;
        int mazeY = newY / speed;
        return mazeX >= 0 && mazeX < labyrinthe.getLabyrinthe()[0].length &&
               mazeY >= 0 && mazeY < labyrinthe.getLabyrinthe().length &&
               labyrinthe.getLabyrinthe()[mazeY][mazeX] == 1;
    }

    public void resetMoveFlags() {
        right = false;
        left = false;
        up = false;
        down = false;
    }

    public void render(Graphics g) {
        BufferedImage heroImage;
        switch (direction) {
            case "up":
                heroImage = upImage;
                break;
            case "down":
                heroImage = downImage;
                break;
            case "left":
                heroImage = leftImage;
                break;
            case "right":
                heroImage = rightImage;
                break;
            default:
                heroImage = downImage;
                break;
        }
        
        g.drawImage(heroImage, x, y, speed, speed, null);
    }
}
