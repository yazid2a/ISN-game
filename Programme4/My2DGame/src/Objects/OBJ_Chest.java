package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Chest extends SuperObject{
	GamePanel gp;
	public OBJ_Chest(GamePanel gp) {
	name = "Chest";
	try { 
	image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
	uTool.scaleImage(image, gp.titleSize, gp.titleSize);

	} catch (IOException e) {
		e.printStackTrace();
	}

}

}
