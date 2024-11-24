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
public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40 , arial_80B;
	//BufferedImage keyImage;
	  BufferedImage heart_full, heart_half,heart_blank;
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
		
		//CREATE MUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full=heart.image;
		heart_half=heart.image2;
		heart_blank=heart.image3;
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
			drawPlayerLife();
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
