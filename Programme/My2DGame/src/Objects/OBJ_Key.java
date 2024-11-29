package Objects;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;
public class OBJ_Key extends Entity {
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1=setup("/Objects/key",gp.titleSize,gp.titleSize);
	
}
}