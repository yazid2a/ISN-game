package Objects;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity{
	GamePanel gp;
	int value=5;
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		type = type_consumable;
		name = "potion Rouge";
		down1 = setup("/Objects/potion_red",gp.titleSize,gp.titleSize);
		description="[Potion Rouge]\nRegénére "+value+" points de vie";
		
	}
	public void use(Entity entity) {
		gp.gameState= gp.dialogueState;
		gp.ui.currentDialogue="vous avez bu "+ name + "!\nVous avez retoré"+ value+ "Points de vie";
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(2);
		
	}

}
