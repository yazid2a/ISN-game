package tile_interactive;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;

    public IT_DryTree(GamePanel gp,int col ,int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX=gp.titleSize*col;
        this.worldY=gp.titleSize*row;
        down1 = setup("/tiles_interactive/drytree", gp.titleSize, gp.titleSize);
        destructible = true;
    }
}

