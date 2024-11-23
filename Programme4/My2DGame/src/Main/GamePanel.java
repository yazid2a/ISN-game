package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import Monstre.monster;
import Objects.SuperObject;

import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
	final int originalTitleSize = 16 ;
	final int scale = 3 ;
	public final int titleSize = originalTitleSize * scale ;
	public final int maxScreenCOL = 16 ;
	public final int maxScreenRow = 12 ;
	public final int screenWidth = titleSize * maxScreenCOL ;
	public final int screenHeight = titleSize * maxScreenRow ;
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//to delete after :
	public final int worldWidth = titleSize*maxWorldCol;
	public final int worldHeight = titleSize*maxWorldRow;

	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyh = new KeyHandler(this);
	Sound music=new Sound();
	Sound se = new Sound();
	
	public CollisionCheck cChecker=new CollisionCheck(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui= new UI(this);
	Thread gameThread;
	
	//entity and object
	public Player player = new Player(this,keyh);
	public SuperObject obj[]= new SuperObject[10];
	public Entity npc[]=new Entity[10];
	

	//public ArrayList<monster> monsters = new ArrayList<>();
	public int gameState; 
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState=3;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyh);
		this.setFocusable(true);
		
		
		
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		stopMusic();
		gameState = playState;
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		 // Add monsters to the game
        //monsters.add(new monster(100, 100));
       // monsters.add(new monster(200, 200));
		
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;//0.016 SECONDS 
		double nextDrawTime = System.nanoTime()+ drawInterval;
		while(gameThread != null) {
			//System.out.println("le jeu est commencé");
			update();
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime<0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	public void update() {
		//player.update();
		if(gameState == playState) {
			player.update();
			//NPC
		for (int i=0;i<npc.length;i++) {
			if(npc[i]!=null) npc[i].update();
		}
		}if(gameState == pauseState) {
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		// DEBUG
		long drawStart = 0;
		if (keyh.checkDrawTime == true){
			drawStart = System.nanoTime();
			
		}
		//tile
		tileM.draw(g2);
		
		//object 
		for (int i = 0 ; i< obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//NPC
		for (int i = 0 ; i< npc.length; i++) {
			if (npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		//player
		player.draw(g2);
		//UI
		ui.draw(g2);
		
		// DEBUG
		if (keyh.checkDrawTime == true) {
			long drawEnd= System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: "+passed) ; 
			
		
		}
		g2.dispose();
		}
		
		
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	

}
