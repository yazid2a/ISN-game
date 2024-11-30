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
		int i=0;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.titleSize*21;
		gp.monster[i].worldY=gp.titleSize*38;
		i++;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.titleSize*23;
		gp.monster[i].worldY=gp.titleSize*42;
		i++;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.titleSize*24;
		gp.monster[i].worldY=gp.titleSize*37;
		i++;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.titleSize*34;
		gp.monster[i].worldY=gp.titleSize*42;
		i++;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.titleSize*38;
		gp.monster[i].worldY=gp.titleSize*42;
		i++;
		
	}
}
