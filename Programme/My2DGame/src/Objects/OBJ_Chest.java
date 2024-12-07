package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Chest extends Entity{

	public OBJ_Chest(GamePanel gp) {
		super(gp);
	name = "coffre";
	down1=setup("/Objects/chest",gp.titleSize,gp.titleSize);
}

}
