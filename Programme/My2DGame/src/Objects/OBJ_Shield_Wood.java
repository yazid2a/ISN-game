package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		name="Wood Shield";
		down1=setup("/Objects/shield_wood",gp.titleSize,gp.titleSize);
		defenseValue=1;
		
	}

}
