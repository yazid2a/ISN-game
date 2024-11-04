package game.programme2;

import java.awt.*;
public class hero extends Rectangle{
	private static final long serialVersionUID = 1L;
      
	public boolean right,left,up,down;
	private final int  speed=30;//taille d'une case du labyrinthe
	private labyrinthe labyrinthe;
	
	  public hero(int x, int y,labyrinthe labyrinthe) {
	        this.x = x;
	        this.y = y;
	        this.labyrinthe=labyrinthe;
	       setBounds(x,y,33,33);
	    }
	public void tick() {
		if (right && canMoveTo(x+speed,y)) {
			x+=speed;
			resetMoveFlags();
		}
		if (left  && canMoveTo(x-speed,y)) {
			x-=speed;
			resetMoveFlags();
		}
		if (up  && canMoveTo(x,y-speed)){
			y-=speed;
			resetMoveFlags();
		}
		if (down  && canMoveTo(x,y+speed)) {
			y+=speed;
			resetMoveFlags();
		}
		
		 
	}
	
	private boolean canMoveTo(int newX,int newY) {
		int mazeX=newX/speed;
		int mazeY=newY/speed;
		// Vérifie si les coordonnées sont dans les limites du labyrinthe et qu'il n'y a pas de mur
		return mazeX >= 0 && mazeX < labyrinthe.getLabyrinthe()[0].length &&
				mazeY >= 0 && mazeY < labyrinthe.getLabyrinthe().length &&
				labyrinthe.getLabyrinthe()[mazeY][mazeX] == 1;  
	}
	
	public void resetMoveFlags() {
		right=false;
		left=false;
		up=false;
		down=false;
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x,y,speed,speed);
		
	}
}
