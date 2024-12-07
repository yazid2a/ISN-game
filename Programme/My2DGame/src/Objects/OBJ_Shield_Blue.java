package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Blue extends Entity {
	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		
		type= type_shield;
		name="Bouclier bleu";
		down1=setup("/Objects/shield_blue",gp.titleSize,gp.titleSize);
		defenseValue=3;
		description="[" + name + "]\nbouclier en bleu.";
		
	}

}
