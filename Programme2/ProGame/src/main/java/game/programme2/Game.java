package game.programme2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable,KeyListener{
	
	private boolean isRunning =false;
	public static int WIDTH=635,HEIGHT=480;
	public static final String Title="Pac_Man";
	private Thread thread;

	public static  hero hero;
	private labyrinthe labyrinthe;
	private Monstre monster;
	public Game() {
		Dimension dimension=new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);

	labyrinthe =new labyrinthe(HEIGHT/30,WIDTH/30);//Ajuster le labyrinthe
	//Trouver une position initiale libre pour le heros
	Point initialPos=labyrinthe.findStartingPosition();
	hero = new hero(initialPos.x*30,initialPos.y*30,labyrinthe);//Heros commence au centre de la cellule
	monster = new Monstre(initialPos.x*30,initialPos.y*30,labyrinthe);
	}
	
	public synchronized void start() {

		if(isRunning) return;
		isRunning=true;
		thread= new Thread(this);
		thread.start();
	

	}

	public synchronized void stop() {

		if(!isRunning) return;
		isRunning =false;
		try {
			thread.join();

		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

	
	private void tick() { //mettre à jour l'état du jeu 
	hero.tick();
	monster.tick();
	}

	
	private void render() {// dessiner le contenu à l'écran
		BufferStrategy bs=getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		
		//Dessiner le labyrinthe:
		int[][] layout = labyrinthe.getLabyrinthe();
		  int tileSize = 30; // Taille d'une case du labyrinthe 
		  
		  for (int i = 0; i < layout.length; i++) {
			  for (int j = 0; j < layout[i].length; j++) { 
				  if (layout[i][j] == 1) {
					  g.setColor(Color.black); // Couleur des chemins 
					  g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
					  
				  }else { 
					  g.setColor(Color.gray); // Couleur des murs
					  g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
		} 
				  } 
			  }  

		//Afficher le heros
		hero.render(g);
		monster.render(g);
		// Afficher la barre de vie
		g.setColor(Color.red);
		int barWidth = 100;
		int barHeight = 10;
		int lifePercentage = hero.getVie();
		g.fillRect(10, 10, barWidth, barHeight);
		g.setColor(Color.green);
		g.fillRect(10, 10, (lifePercentage * barWidth) / 100, barHeight);

		
		g.dispose();
		bs.show();
	}



	public void run() {
		requestFocus();
		int fps =0;
		double timer =System.currentTimeMillis();		long lasTime=System.nanoTime();
		double targetTick =60.0; // pour standariser la fréquence de mises à jour effectuées 
		double delta = 0 ;
		double ns=1000000000/targetTick;
	  while(isRunning) {
		    long now =System.nanoTime();
		    delta+=(now-lasTime)/ns;
		    lasTime=now;
		    
      while(delta>=1) {
		   tick();
		   render();//on peut mettre ca en dehors de la boucle
		   fps++;//meme remarque
		   delta --;

		}
      if(System.currentTimeMillis()- timer>=1000) {
        System.out.println(fps);//afficher le nombre total de frames calculées pendant une seconde.
        fps= 0;
        timer+=1000;
	  }
	
	  }
		stop();

	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) hero.right=true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) hero.left=true;
		if(e.getKeyCode()==KeyEvent.VK_UP) hero.up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) hero.down=true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) hero.right=false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) hero.left=false;
		if(e.getKeyCode()==KeyEvent.VK_UP) hero.up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) hero.down=false;
		
		//hero.resetMoveFlags();
		
	}

}
