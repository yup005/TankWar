package pan.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    private Direction direction;
    private boolean stopped;


    private boolean enemy;

    public Tank(int x, int y, Direction direction) {
        this(x, y, direction, false);
    }

    public Tank(int x, int y, Direction direction, boolean enemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
    }

    //改变方向后,图标会变
    Image getImage() {
        //敌方坦克前缀加e
        String prefix = enemy ? "e" : "";
        switch (direction) {
            case UP:
                return Tools.getImage(prefix + "tankU.gif");
            case DOWN:
                return Tools.getImage(prefix + "tankD.gif");
            case LEFT:
                return Tools.getImage(prefix + "tankL.gif");
            case RIGHT:
                return Tools.getImage(prefix + "tankR.gif");
            case UPLEFT:
                return Tools.getImage(prefix + "tankLU.gif");
            case UPRIGHT:
                return Tools.getImage(prefix + "tankRU.gif");
            case DOWNLEFT:
                return Tools.getImage(prefix + "tankLD.gif");
            case DOWNRIGHT:
                return Tools.getImage(prefix + "tankRD.gif");
        }
        return null;
    }

    //绘制坦克
    void draw(Graphics g) {
        //按键后重新定义当前方向
        this.determineDirection();
        //根据当前方向移动
        this.move();
        //绘制当前位置当前方向的坦克
        g.drawImage(this.getImage(), this.x, this.y, null);
    }

    private boolean up, down, left, right;

    //根据输入定义方向,没有输入时定义stopped为true
    private void determineDirection() {
        if (!up && !left && !down && !right) {
            this.stopped = true;
        } else {
            if (up && left && !down && !right) this.direction =
                    Direction.UPLEFT;
            else if (up && !left && !down && right) this.direction =
                    Direction.UPRIGHT;
            else if (!up && !left && down && right) this.direction =
                    Direction.DOWNRIGHT;
            else if (!up && left && down && !right) this.direction =
                    Direction.DOWNLEFT;
            else if (up && !left && !down && !right) this.direction =
                    Direction.UP;
            else if (!up && left && !down && !right) this.direction =
                    Direction.LEFT;
            else if (!up && !left && down && !right) this.direction =
                    Direction.DOWN;
            else if (!up && !left && !down && right) this.direction =
                    Direction.RIGHT;
            this.stopped = false;
        }
    }

    //改变坦克坐标
    void move() {
        if (stopped) return;
        switch (direction) {
            case UP:
                y -= 5;
                break;
            case DOWN:
                y += 5;
                break;
            case LEFT:
                x -= 5;
                break;
            case RIGHT:
                x += 5;
                break;
            case UPLEFT:
                x -= 5;
                y -= 5;
                break;
            case UPRIGHT:
                x += 5;
                y -= 5;
                break;
            case DOWNLEFT:
                x -= 5;
                y += 5;
                break;
            case DOWNRIGHT:
                x += 5;
                y += 5;
                break;
        }
    }

    //输入键盘指令,得到方向
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    //动了完成以后改成false初始状态
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
