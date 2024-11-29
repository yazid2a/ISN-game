package monster;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class MON_GreenSlime extends Entity {
	GamePanel gp;
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type=2;
		
		name="Green Slime";
		speed=1;
		maxLife=4;
		life=maxLife;
		
		solidArea.x=3;
		solidArea.y=10;
		solidArea.width=42;
		solidArea.height=30;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		getImage();
		
	}
	public void getImage() {
		up1=setup("/monster/greenslime_down_1",gp.titleSize,gp.titleSize);
		up2=setup("/monster/greenslime_down_2",gp.titleSize,gp.titleSize);
		down1=setup("/monster/greenslime_down_1",gp.titleSize,gp.titleSize);
		down2=setup("/monster/greenslime_down_2",gp.titleSize,gp.titleSize);
		left1=setup("/monster/greenslime_down_1",gp.titleSize,gp.titleSize);
		left2=setup("/monster/greenslime_down_2",gp.titleSize,gp.titleSize);
		right1=setup("/monster/greenslime_down_1",gp.titleSize,gp.titleSize);
		right2=setup("/monster/greenslime_down_2",gp.titleSize,gp.titleSize);
		
	}
	public void setAction() {
		actionLockCounter ++;
		if(actionLockCounter==120) {
			Random random=new Random();
			int i=random.nextInt(100)+1;
			if(i<=25) directions="up";
			if(i>25 && i<=50) directions="down";
			if(i>50 && i<=75) directions="left";
			if(i>75 && i<=100) directions="right";
			actionLockCounter=0;
		}
		
	}

}
