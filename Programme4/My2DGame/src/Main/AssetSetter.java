package Main;

import Objects.OBJ_Boots;
import Objects.OBJ_Chest;
import Objects.OBJ_Door;
import Objects.OBJ_Key;

public class AssetSetter {
GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
		
	}
	public void setObject() {
		
	}
	public void setNPC(){
	gp.npc[0]=new NPC_OldMan(gp);	
	gp.npc[0].worldX=gp.tileSize*21;
	gp.npc[0].worldY=gp.tileSize*21;
	
	}
}
