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
	public String currentDialogue="";
	
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
		this.g2= g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
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
	private void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "--PauSed--";
		int x = getXforCenteredText(text) ;
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
		
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

	private int getXforCenteredText(String text) {
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
