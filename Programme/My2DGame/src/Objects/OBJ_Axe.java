package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Axe extends Entity{
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		name="Axe";
		type= type_axe;
		down1 = setup("/objects/axe",gp.titleSize,gp.titleSize);
		attackValue=2;
		attackArea.width = 30;
		attackArea.height=30;
		description="[" + name + "]\n pour couper les \n arbres.";
	}
}
