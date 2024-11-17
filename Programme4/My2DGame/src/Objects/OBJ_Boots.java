package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Boots extends SuperObject {
	GamePanel gp;
	public OBJ_Boots(GamePanel gp) {
	name = "Boots";
	try { 
	image = ImageIO.read(getClass().getResourceAsStream("/Objects/boots.png"));
	uTool.scaleImage(image, gp.titleSize, gp.titleSize);
	} catch (IOException e) {
		e.printStackTrace();
	}

}

}
