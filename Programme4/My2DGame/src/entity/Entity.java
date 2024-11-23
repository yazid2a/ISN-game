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
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String directions;
	public int spriteCounter = 0;
	public int spritNum =1;
	public Rectangle solidArea=new Rectangle(0,0,48,48);
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn=false;
	
	public Entity(GamePanel gp) {
		this.gp=gp;
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
	public BufferedImage setup (String imagePath) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	try {
    	image =ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    	image = uTool.scaleImage (image, gp.titleSize, gp.titleSize);
    	} catch (IOException e) {
    	e.printStackTrace();
    	}
    	return image;
    	}
	
	
 
}
