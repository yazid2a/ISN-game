package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed , downPressed , leftPressed , rightPressed,enterPressed;
	boolean checkDrawTime = false;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//play state
		if(gp.gameState==gp.playState) {
			playState(code);
		}
		//Pause state
		else if(gp.gameState==gp.pauseState) {
			pauseState(code);
		}
		
		//Dialogue
		else if(gp.gameState==gp.dialogueState) {
			
			dialogueState(code);
		}
		//Character state
		else if(gp.gameState==gp.characterState) {
		characterState(code);	
		}
		
		
		
		
		
		}
	public void titleState(int code) {
		
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;	
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			
		}
		if(code == KeyEvent.VK_P) {				
				gp.gameState=gp.pauseState;							
			}
		if(code==KeyEvent.VK_C) {
			gp.gameState=gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {				
			enterPressed=true;						
		}
		if (code == KeyEvent.VK_T) {
			if (checkDrawTime == false){
				checkDrawTime = true;
				
			}
			
		
		
		}else if (checkDrawTime == true) {
			checkDrawTime = false;
		
		}
		
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {				
			gp.gameState=gp.playState;							
		}
		
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {				
			gp.gameState=gp.playState;							
		}
		
	}
	public void characterState(int code) {
		if (code==KeyEvent.VK_C) {
			gp.gameState=gp.playState;
		}
		
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			
			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
			
		}
		
	}
	

}
