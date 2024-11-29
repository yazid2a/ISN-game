package Objects;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart extends Entity {
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
	name = "Heart";
	image=setup("/Objects/heart_full",gp.titleSize,gp.titleSize);
	image2=setup("/Objects/heart_half",gp.titleSize,gp.titleSize);
	image3=setup("/Objects/heart_blank",gp.titleSize,gp.titleSize);

}

}   