package entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Rectangle;
public class Entity {
	GamePanel gp;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,
	attackLeft1,attackLeft2,attackRight1,attackRight2; 
	public BufferedImage image,image2,image3;
	public Rectangle solidArea=new Rectangle(0,0,48,48);
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[]=new String[20];

	
	//STATE
	public int worldX, worldY;

	public String directions="down";
	//manque diaglogueIndex
	public int spritNum =1;
	public boolean collisionOn=false;
	public boolean invincible=false;
	boolean attacking=false;

	
	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter=0;
	public int invincibleCounter=0;
	
	//CHARACTER ATTRIBUTES
	public int  maxLife;
	public int life;
	public String name;
	public int type;	// 0=Player ,1=NPC,2=monster
	public int speed;
	
	public Entity(GamePanel gp) {
		this.gp=gp;
	}	
	public void setAction() {}
	public void speak() {
		gp.ui.currentDialogue=dialogues[0];
		switch(gp.player.directions) {
		case "up":
			directions="down";
			break;
		case "down":
			directions="up";
			break;
		case "left":
			directions="right";
			break;
		case "right":
			directions="left";
			break;
		}
	}
	public void update() {
		setAction();
		collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer=gp.cChecker.checkPlayer(this);
		
		if(this.type==2 && contactPlayer == true) {
			if(gp.player.invincible==false) {
				//we can keep damage
				gp.player.life-=1;
				gp.player.invincible=true;
			}
			
			
		}
		// Si la collision est fausse, le joueur peut se déplacer
        if (!collisionOn) {
            switch (directions) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
  

    // Animation du joueur
    spriteCounter++;
    if (spriteCounter > 10) {
        spritNum = (spritNum == 1) ? 2 : 1;
        spriteCounter = 0;
    }
		
	}
	public void draw(Graphics2D g2) {
		 BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			if (directions != null) {
	            switch (directions) {
	                case "up":
	                    image = (spritNum == 1) ? up1 : up2;
	                    break;
	                case "down":
	                    image = (spritNum == 1) ? down1 : down2;
	                    break;
	                case "left":
	                    image = (spritNum == 1) ? left1 : left2;
	                    break;
	                case "right":
	                    image = (spritNum == 1) ? right1 : right2;
	                    break;
	            }
	        } else {
	            // Image par défaut si aucune direction n'est définie
	            image = down1;
	        } 
		}
			g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			
		} 
	public BufferedImage setup (String imagePath,int width,int height) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	try {
    	image =ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    	image = uTool.scaleImage (image,width,height);
    	} catch (IOException e) {
    	e.printStackTrace();
    	}
    	return image;
    	}
	
	
 
}
