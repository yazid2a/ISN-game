package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity {

	public OBJ_Boots(GamePanel gp) {
		super(gp);
	name = "bots";
	down1=setup("/Objects/boots",gp.titleSize,gp.titleSize);

}

}
