package entity;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Color;
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
	public Rectangle attackArea=new Rectangle(0,0,0,0);
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
	public boolean alive =true;
	public boolean dying=false;
	boolean hpBarOn=false;
	
	
	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter=0;
	public int invincibleCounter=0;
	int dyingCounter=0;
	int hpBarCounter=0;
	
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
	public void damageReaction() {}
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
				gp.playSE(6);
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
             if(invincible==true) {
            	invincibleCounter++;
            	if(invincibleCounter>40) {
            		invincible=false;
            		invincibleCounter=0;
            	}
            	
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
			
	            switch (directions) {
	                case "up":
	                    if(spritNum == 1) {image=up1;}
	                    if(spritNum == 2) {image=up2;}
	                    break;
	                case "down":
	                	  if(spritNum == 1) {image=down1;}
		                    if(spritNum == 2) {image=down2;}
		                    break;
	                case "left":
	                	  if(spritNum == 1) {image=left1;}
		                    if(spritNum == 2) {image=left2;}
		                    break;
	                case "right":
	                	  if(spritNum == 1) {image=right1;}
		                    if(spritNum == 2) {image=right2;}
		                    break;
	            }
	            
	            
	            //monster HP bar
	         
	            if(type ==2 && hpBarOn==true) {
	            	double oneScale= (double)gp.titleSize/maxLife;
	            	double hpBarValue = oneScale*life;
	            	
	            g2.setColor(new Color(35,35,35));
	            g2.fillRect(screenX-1, screenY-16, gp.titleSize+2,12);
	            	
	            g2.setColor(new Color(255,0,30));
	            g2.fillRect(screenX,screenY-15,(int)hpBarValue,10);
	            hpBarCounter++;
	            
	            if(hpBarCounter>600) {
	            	hpBarCounter=0;
	            	hpBarOn=false;
	            }
	            }
	            if(invincible==true) {
	            	hpBarOn=true;
	            	hpBarCounter=0;
	            	changeAlpha(g2,0.4F);	
	            }
	            if(dying==true) {
	            	dyingAnimation(g2);
	            }
	       
		
			g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			changeAlpha(g2,1F);
		}
		}
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter ++;
		int i=5;
		if(dyingCounter<=i) {changeAlpha(g2,0f);}
		if(dyingCounter>i && dyingCounter<=i*2) {changeAlpha(g2,1f);}
		if(dyingCounter>i*2 && dyingCounter>=i*3) {changeAlpha(g2,0f);}
		if(dyingCounter>i*3 && dyingCounter<=i*4) {changeAlpha(g2,1f);}
		if(dyingCounter>i*4 && dyingCounter<=i*5) {changeAlpha(g2,0f);}
		if(dyingCounter>i*5 && dyingCounter<=i*6) {changeAlpha(g2,1f);}
		if(dyingCounter>i*6 && dyingCounter<=i*7) {changeAlpha(g2,0f);}
		if(dyingCounter>i*7 && dyingCounter<=i*8) {changeAlpha(g2,1f);}
		if(dyingCounter>i*8) {
			dying=false;
			alive=false;
		}
	}
	
	public void changeAlpha(Graphics2D g2,float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
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
