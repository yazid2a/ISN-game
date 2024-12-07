package entity;

import Main.GamePanel;

public class Projectile extends Entity{
	Entity user;
	public Projectile(GamePanel gp) {
		super(gp);
	}
	public void set(int worldX , int worldY,String directions,boolean alive, Entity user) {
		
		this.worldX= worldX;
		this.worldY=worldY;
		this.directions = directions;
		this.alive =alive;
		this.user= user;
		this.life=this.maxLife;
		
	}
	public void update() {
		if(user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex,attack);
				alive=false;
			}
		}
		if(user != gp.player) {
			
		}

		switch (directions) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life--;
		if(life<= 0) {
			alive =false;
		}
		spriteCounter++;
		if (spriteCounter > 12) {
		if (spritNum == 1) {
		spritNum = 2;
		}
		else if (spritNum ==2) {
		spritNum = 1;
		}
		spriteCounter = 0;
		}
		
	}

}
