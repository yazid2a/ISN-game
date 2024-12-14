package tile_interactive;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class InteractiveTile extends Entity{

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp,int col ,int row ) {
        super(gp);
        this.gp = gp;
    }

    public void update() {

    }
}
