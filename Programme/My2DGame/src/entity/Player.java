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
        
        attackArea.width=36;
        attackArea.height=36;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
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
        
            up1 = setup("/player/boy_up_1",gp.titleSize,gp.titleSize);
            up2 = setup("/player/boy_up_2",gp.titleSize,gp.titleSize);
            down1 = setup("/player/boy_down_1",gp.titleSize,gp.titleSize);
            down2 = setup("/player/boy_down_2",gp.titleSize,gp.titleSize);
            left1 = setup("/player/boy_left_1",gp.titleSize,gp.titleSize);
            left2 = setup("/player/boy_left_2",gp.titleSize,gp.titleSize);
            right1 = setup("/player/boy_right_1",gp.titleSize,gp.titleSize);
            right2 = setup("/player/boy_right_2",gp.titleSize,gp.titleSize);
        
    }
    public void getPlayerAttackImage() {
    	 attackUp1 = setup("/player/boy_attack_up_1",gp.titleSize,gp.titleSize*2);
         attackUp2 = setup("/player/boy_attack_up_2",gp.titleSize,gp.titleSize*2);
         attackDown1 = setup("/player/boy_attack_down_1",gp.titleSize,gp.titleSize*2);
         attackDown2 = setup("/player/boy_attack_down_2",gp.titleSize,gp.titleSize*2);
         attackLeft1 = setup("/player/boy_attack_left_1",gp.titleSize*2,gp.titleSize);
         attackLeft2 = setup("/player/boy_attack_left_2",gp.titleSize*2,gp.titleSize);
         attackRight1 = setup("/player/boy_attack_right_1",gp.titleSize*2,gp.titleSize);
         attackRight2 = setup("/player/boy_attack_right_2",gp.titleSize*2,gp.titleSize);
     
    }
   
    public void update() {
    	
    	if(attacking==true) {
    		attacking();
    		
    	}
    	else if(keyH.upPressed==true||keyH.downPressed==true||
    	keyH.leftPressed==true||keyH.rightPressed==true||keyH.enterPressed==true) {
       
    	if (keyH.upPressed==true) {
            directions = "up";
     
        } else if (keyH.downPressed==true) {
            directions = "down";
         
        } else if (keyH.leftPressed==true) {
            directions = "left";
    
        } else if (keyH.rightPressed==true) {
            directions = "right";
        }
 
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

public void attacking() {
	
	spriteCounter++;
	if(spriteCounter<=5) {
		spritNum=1;
	}
	if(spriteCounter>5 && spriteCounter<=25) {
		spritNum=2;
		
		
		//Save the current worldX,worldY,solidArea
		int currentWorldX=worldX;
		int currentWorldY=worldY;
		int solidAreaWidth=solidArea.width;
		int solidAreaHeight=solidArea.height;
		
		//adjust player's worldX/Y for  the attackArea
		switch(directions) {
		case "up":worldY-=attackArea.height;break;
		case "down":worldY+=attackArea.height;break;
		case "left":worldX-=attackArea.width;break;
		case "right":worldX+=attackArea.width;break;
		}
		//attackArea becomes solidArea
		solidArea.width = attackArea.width;
		solidArea.height = attackArea.height;
		// check monster collision with the updated worldX,worldY and solidArea
		int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
		damageMonster(monsterIndex);
		
		//after checking collision, remote the original data
		worldX = currentWorldX;
		worldY = currentWorldY;
		solidArea.width=solidAreaWidth;
		solidArea.height= solidAreaHeight;	
	}
	if(spriteCounter>25) {
		spritNum=1;
		spriteCounter=0;
		attacking=false;
	}
}
    
    
    public void pickUpObject(int i) {
    	if (i != 999) {
    		
    	}
    	
    }
    public void intercatNPC(int i) {
    	if(gp.keyH.enterPressed==true) {
    		if (i != 999) {
    				gp.gameState=gp.dialogueState;
    				gp.npc[i].speak();
	
    		    	}
    		else {
    			attacking=true;
    		}	
    	}
    }
    public void damageMonster(int i) {
    	if(i!=999) {
    		if(gp.monster[i].invincible == false) {
    			gp.monster[i].life-=1;
    			gp.monster[i].invincible=true;
    			
    			if(gp.monster[i].life<=0) {
    				gp.monster[i]=null;
    			}
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
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        // Choix de l'image en fonction de la direction et de l'animation
        //if (directions != null) {
            switch (directions) {
                case "up":
                	if(attacking==false) {
                		if(spritNum==1) {image=up1;}
                        if(spritNum == 2) {image = up2;}
                	}
                	if(attacking==true) {
                		tempScreenY=screenY- gp.titleSize;
                		if(spritNum==1) {image=attackUp1;}
                        if(spritNum == 2) {image = attackUp2;}
                	}
                    break;
                case "down":
                	if(attacking==false) {
                		if(spritNum==1) {image=down1;}
                        if(spritNum == 2) {image = down2;}
                	}
                	if(attacking==true) {
                		if(spritNum==1) {image=attackDown1;}
                        if(spritNum == 2) {image = attackDown2;}
                	}
                    break;
                case "left":
                	if(attacking==false) {
                		if(spritNum==1) {image=left1;}
                        if(spritNum == 2) {image = left2;}
                	}
                	if(attacking==true) {
                		tempScreenX=screenX-gp.titleSize;
                		if(spritNum==1) {image=attackLeft1;}
                        if(spritNum == 2) {image = attackLeft2;}
                	}
                    break;
                case "right":
                	if(attacking==false) {
                		if(spritNum==1) {image=right1;}
                        if(spritNum == 2) {image = right2;}
                	}
                	if(attacking==true) {
                		if(spritNum==1) {image=attackRight1;}
                        if(spritNum == 2) {image = attackRight2;}
                	}
                    break;
            }
     
            if(invincible==true) {
            	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
            }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        //reste Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        //DEBUG
      //  g2.setFont(new Font("Arial",Font.PLAIN,26));
       // g2.setColor(Color.white);
       // g2.drawString("Invincible:"+invincibleCounter, 10, 400);
        
    }
        }
