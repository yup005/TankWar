package pan.tankwar;

import javax.swing.*;
import java.awt.*;

public class Wall {
    //一块墙是30*28
    private int x;
    private int y;
    private boolean horizontal;
    private int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g) {
        Image brickImage = Tools.getImage("brick.png");
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(brickImage, x+i*brickImage.getWidth(null),y,null);
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(brickImage, x,y+i*brickImage.getHeight(null),null);
            }
        }
    }
}
