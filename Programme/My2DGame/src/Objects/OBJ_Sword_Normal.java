package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		name="Normal Sword";
		down1=setup("/Objects/sword_normal",gp.titleSize,gp.titleSize);
		attackValue=4;
	}

}
