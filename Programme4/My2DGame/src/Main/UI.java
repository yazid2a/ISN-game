package Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Objects.OBJ_Key;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40 , arial_80B;
	//BufferedImage keyImage;
	public boolean messageOn =false;
	public String message="";
	int messageCounter=0;
	
	public boolean gameFinished = false;
	public String currentDialogue="";
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40=new Font("Arial" , Font.PLAIN, 40);
		arial_80B=new Font("Arial" , Font.BOLD, 80);
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
		
	}
	
	public void showMessage(String text) {
		message=text;
		messageOn=true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2= g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//play state
		if(gp.gameState == gp.playState) {
			
		}
		//pausestate
		if(gp.gameState== gp.pauseState) {
			drawPauseScreen();
			
		}
		//dialogue
		if(gp.gameState==gp.dialogueState) {
			drawDialogueScreen();
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
		g2.drawString(currentDialogue,x,y);
		
	}
	public void drawSubWindow(int x,int y,int width,int height) {
		Color c= new Color(0,0,0,200);
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
}
