import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class GameFrame extends Frame{
    Image bgImg=GameUtil.getImage("images/bg.jpg");
    Image planeImg=GameUtil.getImage("images/plane.png");
    int x=200,y=200;  //飞机坐标

    Plane plane=new Plane(planeImg,200,200,5);
    Shell[] shells=new Shell[30];
    Explode explode;

    Date startTime = new Date();    //游戏起始时刻
    Date endTime;   //游戏结束时刻
    int period;  //玩了多少秒

    //初始化窗口
    public void launchFrame(){
        this.setTitle("空战");
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocation(300,300);
        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        //启动窗口绘制线程
        new PaintThread().start();
        //启动键盘监听
        this.addKeyListener(new KeyMonitor());
        //初始化炮弹数组
        for (int i=0;i<shells.length;i++){
            shells[i]=new Shell();
        }
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(bgImg,0,0,GameUtil.FRAME_WIDTH,GameUtil.FRAME_HIGHT,null);
        plane.drawMyself(g);
        for(int i=0;i<shells.length;i++){
            if(shells[i]!=null){
                shells[i].drawMyself(g);
                boolean fail=shells[i].getRec().intersects(plane.getRec());
                if(fail&&plane.alive){
                    plane.alive=false;
                    endTime=new Date();
                    period=(int)((endTime.getTime()-startTime.getTime())/1000);
                    if(explode==null){
                        explode=new Explode(plane.x, plane.y);
                    }
                    explode.draw(g);

                }
            }
        }
        if (plane.alive==false){
            paintInfo(g,"游戏时间："+period+"秒",40,200,200,Color.white);
        }
        x-=3;
        y-=3;
    }

    public void paintInfo(Graphics g,String str,int size,int x,int y,Color color){
        Font oldFont=g.getFont();
        Color oldColor=g.getColor();

        Font f=new Font("宋体",Font.BOLD,size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str,x,y);

        g.setFont(oldFont);
        g.setColor(oldColor);
    }

    //键盘监听内部类
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }

    //重画线程
    class PaintThread extends Thread {
        @Override
        public void run(){
            while (true){
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        GameFrame frame=new GameFrame();
        frame.launchFrame();
    }

    private Image offScreenImage=null;
    public void Update(Graphics g){
        if(offScreenImage==null)
            offScreenImage=this.createImage(GameUtil.FRAME_WIDTH,GameUtil.FRAME_HIGHT);  //参数为游戏窗口的宽度和高度
        Graphics gOff=offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
