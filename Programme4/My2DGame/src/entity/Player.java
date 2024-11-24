package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import Objects.OBJ_Heart;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    int counter2 = 0;    
    
    public Player(GamePanel gp, KeyHandler keyH) {
    	super(gp);
       
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.titleSize/2);
        screenY = gp.screenHeight/2 - (gp.titleSize/2);
        
        solidArea =new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.titleSize*23;
        worldY = gp.titleSize*21;
        speed = 4;
        directions ="down"; 
        
        //PLAYER STATUS
        maxLife =6;
        life= maxLife;
    }

    public void getPlayerImage() {
        
            up1 = setup("/player/boy_up_1");
            up2 = setup("/player/boy_up_2");
            down1 = setup("/player/boy_down_1");
            down2 = setup("/player/boy_down_2");
            left1 = setup("/player/boy_left_1");
            left2 = setup("/player/boy_left_2");
            right1 = setup("/player/boy_right_1");
            right2 = setup("/player/boy_right_2");
        
    }
   
    public void update() {
    	if(keyH.upPressed==true||keyH.downPressed==true||
    	keyH.leftPressed==true||keyH.rightPressed==true||keyH.enterPressed==true) {
       
    	if (keyH.upPressed==true) {
            directions = "up";
     
        } else if (keyH.downPressed==true) {
            directions = "down";
         
        } else if (keyH.leftPressed==true) {
            directions = "left";
    
        } else if (keyH.rightPressed==true) {
            directions = "right";
        } //else {
           // directions = null; // Si aucune touche n'est pressée, la direction est null
       // }

       
        
          
        //if (directions != null) {
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //verifier collision avec objet
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            //verifier collision avec NPC
            int npcIndex=gp.cChecker.checkEntity(this, gp.npc);
            intercatNPC(npcIndex);
            
            //verifie la collision avec monster
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);
            
            
            //check EVENT
            gp.eHandler.checkEvent();

            //gp.keyH.enterPressed=false;
            // Si la collision est fausse, le joueur peut se déplacer
            //if (!collisionOn) {
            if(collisionOn==false && gp.keyH.enterPressed==false) {
                switch (directions) {
                    case "up":worldY -= speed;
                        break;
                    case "down":worldY += speed;
                        break;
                    case "left":worldX -= speed;
                        break;
                    case "right": worldX += speed;
                        break;
                }
            }
            gp.keyH.enterPressed=false;
        //}
        spriteCounter++;
        if(spriteCounter>12) {
        	if(spritNum==1) {
        		spritNum=2;
        	}
        	else if(spritNum==2) {
        		spritNum=1;
        	}
        	spriteCounter=0;
        }
        }
    	else {
    		standCounter++;
    		if(standCounter==20) {
    			spritNum=1;
    			standCounter=0;
    			
    		}
    		}
    	 //This needs to be outside of key if statement!
        if(invincible==true) {
        	invincibleCounter++;
        	if(invincibleCounter>60) {
        		invincible=false;
        		invincibleCounter=0;
        	}
        	
        }
    	}

        // Animation du joueur
       // spriteCounter++;
        //if (spriteCounter > 10) {
           // spritNum = (spritNum == 1) ? 2 : 1;
            //spriteCounter = 0;
       // }
   // }
    
    
    
    public void pickUpObject(int i) {
    	if (i != 999) {
    		
    	}
    	
    }
    public void intercatNPC(int i) {
if (i != 999) {
	if(gp.keyH.enterPressed==true) {
		gp.gameState=gp.dialogueState;
		gp.npc[i].speak();
	}
	
    		
    	}
    }
    
    public void contactMonster(int i) {
    	if(i!=999) {
    		if(invincible==false) {
    			life-=1;
    			invincible=true;
    		}
    	}
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Choix de l'image en fonction de la direction et de l'animation
        //if (directions != null) {
            switch (directions) {
                case "up":
                	if(spritNum==1) {
                		image=up1;
                	}
                    if(spritNum == 2) { 
                    	image = up2;}
                    break;
                case "down":
                	if(spritNum==1) {
                		image=down1;
                	}
                    if(spritNum == 2) { 
                    	image = down2;}
                    break;
                case "left":
                	if(spritNum==1) {
                		image=left1;
                	}
                    if(spritNum == 2) { 
                    	image = left2;}
                    break;
                case "right":
                	if(spritNum==1) {
                		image=right1;
                	}
                    if(spritNum == 2) { 
                    	image = right2;}
                    break;
            }
        //} else {
            // Image par défaut si aucune direction n'est définie
            //image = down1;
       // }
            
            if(invincible==true) {
            	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
            }

        g2.drawImage(image, screenX, screenY, null);
        
        //reste Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        //DEBUG
      //  g2.setFont(new Font("Arial",Font.PLAIN,26));
       // g2.setColor(Color.white);
       // g2.drawString("Invincible:"+invincibleCounter, 10, 400);
        
    }
        }
