package Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import Objects.OBJ_Key;
import Objects.OBJ_Heart;
import entity.Entity;
import java.util.ArrayList;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40 , arial_80B;
	//BufferedImage keyImage;
	  BufferedImage heart_full, heart_half,heart_blank;
	public boolean messageOn =false;
	//public String message="";
	//int messageCounter=0;
	ArrayList<String> message=new ArrayList<>();
	ArrayList<Integer> messageCounter=new ArrayList<>();
	public boolean gameFinished = false;
	double playTime;
	DecimalFormat dFormat=new DecimalFormat("#0.00");
	public String currentDialogue="";
	public int commandNum=0;
	
	public int slotCol=0;
	public int slotRow=0;
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40=new Font("Arial" , Font.PLAIN, 40);
		arial_80B=new Font("Arial" , Font.BOLD, 80);
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
		
		//CREATE MUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full=heart.image;
		heart_half=heart.image2;
		heart_blank=heart.image3;
	}
	
	public void addMessage(String text) {
		//message=text;
		//messageOn=true;
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		if(gameFinished==true) {
			
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textLength;
			int x;
			int y;
			
			text="tu as trouvé le trésor!";
			textLength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2-textLength/2;
			 y = gp.screenHeight/2 - (gp.titleSize*3);
			 g2.drawString(text, x, y);
			 
			 text="votre temps est : "+ dFormat.format(playTime)+ "!";
				textLength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				 x = gp.screenWidth/2-textLength/2;
				 y = gp.screenHeight/2 + (gp.titleSize*4);
				 g2.drawString(text, x, y);
			 
			 g2.setFont(arial_80B);
			 g2.setColor(Color.yellow);
			 text="Félicitation!";
			textLength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2-textLength/2;
			 y = gp.screenHeight/2 + (gp.titleSize*2);
			 g2.drawString(text, x, y);
			 gp.gameThread= null; 
		}
		else {
		this.g2= g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// time 
		playTime+=(double)1/60;
		g2.drawString("Time : "+dFormat.format(playTime), gp.titleSize*11, 65);
		
		
		if(gp.gameState==gp.titleState) {
			drawTitleScreen();
		}
		//play state
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		//pausestate
		if(gp.gameState== gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
			
			
		}
		//dialogue
		if(gp.gameState==gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		// charactere state
		if(gp.gameState==gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		if(gp.gameState==gp.gameOverState) {
			
			drawGameOverScreen();
		}
}
	}
	//Dessiner le maximum de vies:
	


	public void drawPlayerLife() {
		//gp.player.life=5;
		int x=gp.titleSize/2;
		int y=gp.titleSize/2;
		int i=0;
		
		while(i<gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+=gp.titleSize;
		}
		//RESET:
			x=gp.titleSize/2;
		 	y=gp.titleSize/2;
		 	i=0;
		//Dessiner la vie actuelle:
		while(i<gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i<gp.player.life) {
				g2.drawImage(heart_full, x, y, null);	
			}
			i++;
			x+=gp.titleSize;
			
		}
	}
	public void drawMessage() {
		int messageX=gp.titleSize;
		int messageY=gp.titleSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		for(int i=0;i<message.size();i++) {
			if(message.get(i)!=null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i),messageX+2,messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i),messageX,messageY);
				int counter=messageCounter.get(i)+1;
				messageCounter.set(i,counter);
				messageY+=50;
				if(messageCounter.get(i)>180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "--PauSed--";
		int x = getXforCenteredText(text) ;
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
		
	}
public void drawTitleScreen()
{
	g2.setColor(new Color(20,60,40));
	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight); 
	// TITLE NAME 
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F)); 
	String text = "Saga du Héros";
	int x = getXforCenteredText(text); 
	int y = gp.titleSize*1; // SHADOW
	g2.setColor(Color.gray); 
	g2.drawString(text, x-160, y); // MAIN COLOR 
	g2.setColor(Color.white); 
	g2.drawString(text, x-160, y);
	// BLUE BOY IMAGE
	x = gp.screenWidth / 2 - (gp.titleSize * 2) / 2;
	y += gp.titleSize * 2;
	g2.drawImage(gp.player.down1, x, y-50, gp.titleSize * 2, gp.titleSize * 2, null);
	// MENU
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

	text = "Nouvelle Partie";
	x = getXforCenteredText(text);
	y += gp.titleSize*3;
	g2.drawString(text, x-80, y-50);
	
	g2.drawString("comment jouer?",gp.titleSize*3,gp.titleSize*7);
	g2.drawString("il faut choisir le mode QWERTY",gp.titleSize*3,gp.titleSize*8);
	g2.drawString("W : aller en haut | S : aller en bas ",gp.titleSize*3,gp.titleSize*9);
	g2.drawString("Q : aller en gauche | D : aller en droite",gp.titleSize*3,gp.titleSize*10);
	g2.drawString("Entré / F : FIGHT! | C = inventaire",gp.titleSize*3,gp.titleSize*11);
	if(commandNum == 0) { g2.drawString(">", x-80-gp.titleSize, y-50); }

	/*text = "Charger une Partie";
	x = getXforCenteredText(text);
	y += gp.titleSize;
	g2.drawString(text, x-80, y-50);
	if(commandNum == 1) { g2.drawString(">", x-80-gp.titleSize, y-50); }*/

	text = "Quitter";
	x = getXforCenteredText(text);
	y += gp.titleSize;
	g2.drawString(text, x-80, y-50);
	if(commandNum == 1) { g2.drawString(">", x-80-gp.titleSize, y-50); }

	
}
	public void drawDialogueScreen() {
		//Window
		int x=gp.titleSize*2-20;
		int y=gp.titleSize/2;
		int width=gp.screenWidth-(gp.titleSize*4);
		int height=gp.titleSize*3;
		
		drawSubWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x+=gp.titleSize;
		y+=gp.titleSize;
		for (String line :currentDialogue.split("\n")) {
			g2.drawString(line,x,y);
			y+=40;
		}
		
		
	}
	public void drawCharacterScreen() {
		//Create Frame
		final int frameX=gp.titleSize;
		final int frameY=gp.titleSize;
		final int frameWidth=gp.titleSize*5;
		final int frameHeight=gp.titleSize*10;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		//Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX=frameX+20;
		int textY=frameY+gp.titleSize;
		final int lineHeight=35;
		//Names
		g2.drawString("Level",textX,textY);
		textY +=lineHeight;
		g2.drawString("Life",textX,textY);
		textY +=lineHeight;
		g2.drawString("Strength",textX,textY);
		textY +=lineHeight;
		g2.drawString("Dexterity",textX,textY);
		textY +=lineHeight;
		g2.drawString("Attack",textX,textY);
		textY +=lineHeight;
		g2.drawString("Defense",textX,textY);
		textY +=lineHeight;
		g2.drawString("Exp",textX,textY);
		textY +=lineHeight;
		g2.drawString("Next Level",textX,textY);
		textY +=lineHeight;
		g2.drawString("Coin",textX,textY);
		textY +=lineHeight+20;
		g2.drawString("Weapon",textX,textY);
		textY +=lineHeight+15;
		g2.drawString("Shield",textX,textY);
		textY +=lineHeight;
		
		//Vqlues
		int tailX=(frameX+frameWidth)-30;
		//Reset textY
		textY=frameY+gp.titleSize;
		String value;
		value=String.valueOf(gp.player.level);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		
		value=String.valueOf(gp.player.life+"/"+gp.player.maxLife);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.strength);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.dexterity);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.attack);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.defense);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.exp);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.nextLevelExp);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		value=String.valueOf(gp.player.coin);
		textX=getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY +=lineHeight;
		g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.titleSize,textY-14,null);
		textY +=gp.titleSize;
		g2.drawImage(gp.player.currentShield.down1,tailX-gp.titleSize,textY-14,null);
		
		
	}
	
	public void drawInventory() {
		int frameX= gp.titleSize*9;
		int frameY = gp.titleSize;
		int frameWidth = gp.titleSize*6;
		int frameHeight = gp.titleSize*5;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY +20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		
		int slotSize = gp.titleSize+3;
		 
		// draw player's items :
		
		
		
		for(int i = 0;i< gp.player.inventory.size(); i++){
			//equipe cursor
			if(gp.player.inventory.get(i) ==  gp.player.currentWeapon || 
					gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.titleSize, gp.titleSize, 10, 10);
			}
			g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);
			
			slotX += slotSize;
			
			if ( i ==4 || i ==9 || i ==14) {
				slotX= slotXstart;
				slotY += slotSize;
			}
		}
		
		// cursor
		int cursorX = slotXstart+ (slotSize * slotCol);
		int cursorY = slotYstart+ (slotSize * slotRow);
		int cursorWidth = gp.titleSize;
		int cursorHeight = gp.titleSize;
		
		//draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY,cursorWidth, cursorHeight,10,10);
		
		// description frame
		
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.titleSize*3;
		
		
		//draw description text
		int textX = dFrameX + 20;
		int textY = dFrameY +gp.titleSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		if (itemIndex <gp.player.inventory.size()) {
			drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32; 
				
			}
			}
		
		
	}
	public void drawGameOverScreen() {

	    g2.setColor(new Color(0, 0, 150,100));
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    int x;
	    int y;
	    String text;
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));

	    text = "Game Over";

	    // Shadow
	    g2.setColor(Color.black);
	    x = getXforCenteredText(text);
	    y = gp.titleSize * 4;
	    g2.drawString(text, x-180, y-50);

	    // Main
	    g2.setColor(Color.white);
	    g2.drawString(text, x - 180, y - 50);
	 // Retry
	    g2.setFont(g2.getFont().deriveFont(25F));
	    text = "Rejouer";
	    x = getXforCenteredText(text);
	    y += gp.titleSize * 4;
	    g2.drawString(text, x-70, y-120);
	    if(commandNum == 0) { g2.drawString(">", x-70-gp.titleSize, y-120); }

	    // Back to the title screen
	    text = "Quitter";
	    x = getXforCenteredText(text);
	    y += 55;
	    g2.drawString(text, x-70, y-120);
	    if(commandNum == 1) { g2.drawString(">", x-70-gp.titleSize, y-120); }

	}


	public int getItemIndexOnSlot() {
		int itemIndex=slotCol+ (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x,int y,int width,int height) {
		Color c= new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x,y,width,height,35,35);
		c=new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
	}

	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2;
		
		return x;
	}
	private int getXforAlignToRightText(String text,int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX-length;		
		return x;
	}
}
