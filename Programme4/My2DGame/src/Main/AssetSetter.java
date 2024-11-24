package Main;

import Objects.OBJ_Boots;

import Objects.OBJ_Chest;
import Objects.OBJ_Door;
import Objects.OBJ_Key;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;

public class AssetSetter {
GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
		
	}
	public void setObject() {
		
	}
	public void setNPC(){
	gp.npc[0]=new NPC_OldMan(gp);	
	gp.npc[0].worldX=gp.titleSize*21;
	gp.npc[0].worldY=gp.titleSize*21;
	
	}
	public void setMonster() {
		gp.monster[0]=new MON_GreenSlime(gp);
		gp.monster[0].worldX=gp.titleSize*23;
		gp.monster[0].worldY=gp.titleSize*36;
		
		gp.monster[1]=new MON_GreenSlime(gp);
		gp.monster[1].worldX=gp.titleSize*23;
		gp.monster[1].worldY=gp.titleSize*37;
	}
}
