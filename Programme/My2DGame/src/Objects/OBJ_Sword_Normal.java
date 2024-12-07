package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		type= type_sword;
		name="épée normale";
		down1=setup("/Objects/sword_normal",gp.titleSize,gp.titleSize);
		attackValue=4;
		attackArea.width = 36;
		attackArea.height=36;
		description="[" + name + "]\nUne ancien épée.";
		

	}

}
