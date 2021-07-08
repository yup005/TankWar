package pan.tankwar;

import javax.swing.*;
import java.awt.*;
//JComponent就属于swing
public class GameClient extends JComponent {
    public GameClient() {
        //创建界面
        this.setPreferredSize(new Dimension(800, 600));
    }

    //画一些东西用的,直接调用的
    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(),
                400, 100,null);
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
    }
}
