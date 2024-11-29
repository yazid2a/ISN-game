package entity;

import Main.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity{

	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		directions="down";
		speed=1;
		getImage();
		setDialogue();
	}
	public void getImage() {
    
    up1 = setup("/npc/oldman_up_1",gp.titleSize,gp.titleSize);
    up2 = setup("/npc/oldman_up_2",gp.titleSize,gp.titleSize);
    down1 = setup("/npc/oldman_down_1",gp.titleSize,gp.titleSize);
    down2 = setup("/npc/oldman_down_2",gp.titleSize,gp.titleSize);
    left1 = setup("/npc/oldman_left_1",gp.titleSize,gp.titleSize);
    left2 = setup("/npc/oldman_left_2",gp.titleSize,gp.titleSize);
    right1 = setup("/npc/oldman_right_1",gp.titleSize,gp.titleSize);
    right2 = setup("/npc/oldman_right_2",gp.titleSize,gp.titleSize);

}
	public void setDialogue() {
		dialogues[0]="Hello!!                             Press Enter";
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
	public void speak() {
		super.speak();
		
	}
}