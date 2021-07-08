package pan.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//JComponent就属于swing
public class GameClient extends JComponent {
    //定义玩家控制的坦克
    private Tank playerTank;
    //定义敌方坦克
    private List<Tank> enemyTanks;

    private List<Wall> walls;

    //初始化
    public GameClient() {
        //创建界面
        this.setPreferredSize(new Dimension(800, 600));

        //我方坦克,默认生成位置在400,100处,朝下
        this.playerTank = new Tank(400, 100, Direction.DOWN);

        //敌方坦克
        this.enemyTanks = new ArrayList<>(12);
        this.walls = Arrays.asList(
                new Wall(200, 140, true, 15),
                new Wall(200, 540, true, 15),
                new Wall(100, 80, false, 15),
                new Wall(700, 80, false, 15)
        );
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                this.enemyTanks.add(new Tank(200 + 120 * j, 400 + 40 * i, Direction.UP,
                        true));
            }
        }
    }

    //画一些东西用的,直接调用的
    //似乎这个会一直自动调用?
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        playerTank.draw(g);
        for (Tank tank : enemyTanks) {
            tank.draw(g);
        }
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Tank War");//设置标题
        //设置标题的图标,不过似乎没显示,可能是因为Mac系统
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());

        GameClient client = new GameClient();
        //client.repaint();
        frame.add(client);//把界面加入这个框架里

        frame.pack();//自适应尺寸,不然就会最小化显示
        frame.setVisible(true);//设置成可见的
        frame.setLocationRelativeTo(null);//让窗口居中显示

        //鼠标点击后结束进程,不然他还在后台没关掉
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //移动坦克,键盘有输入,就运行一次
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                client.playerTank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                client.playerTank.keyReleased(e);
            }
        });

        //一直重新绘制,这样才能动
        while (true) {
            client.repaint();
            try {
                Thread.sleep(50);//50ms一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
