package Objects;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Heart extends SuperObject {
	
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
	name = "Heart";
	try { 
	image = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_full.png"));
	image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_half.png"));
	image3 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_blank.png"));
	image=uTool.scaleImage(image, gp.titleSize, gp.titleSize);
	image2=uTool.scaleImage(image2, gp.titleSize, gp.titleSize);
	image3=uTool.scaleImage(image3, gp.titleSize, gp.titleSize);
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
	
	
}

}   