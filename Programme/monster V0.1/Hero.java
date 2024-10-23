package acl;

public class Hero {
    private int x, y;
    private int health;

    public Hero(int startX, int startY,int h) {
        this.x = startX;
        this.y = startY;
        this.health=h;
    }

    public void deplacer(int dx, int dy, int[][] labyrinthe) {
        if (x + dx >= 0 && x + dx < labyrinthe.length && 
            y + dy >= 0 && y + dy < labyrinthe[0].length && 
            labyrinthe[x + dx][y + dy] != 1) {
            x += dx;
            y += dy;
        }
    }

    public boolean detecterCollision(Monstre monstre) {
        return this.x == monstre.getX() && this.y == monstre.getY();
        
    }

    public void perdreVie() {
        health -= 20;
        if (health <= 0) {
        	System.out.println("Game Over!");
        }
    }
    public int geth() {
        return health;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

