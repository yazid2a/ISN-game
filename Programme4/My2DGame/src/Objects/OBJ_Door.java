package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Door extends SuperObject {
	GamePanel gp;
	public OBJ_Door(GamePanel gp) {
	name = "Door";
	try { 
	image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
	uTool.scaleImage(image, gp.titleSize, gp.titleSize);
	} catch (IOException e) {
		e.printStackTrace();
	}
	collision =true;

}

}
