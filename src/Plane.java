import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
    boolean left,right,up,down;
    boolean alive = true;

    //按下上下左右键改变方向
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left=true;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right=true;
                break;
            default:
                break;
        }
    }
    //松开上下左右键
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up=false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                down=false;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left=false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right=false;
                break;
            default:
                break;
        }
    }
    @Override
    public void drawMyself(Graphics g){
        if(alive){
            super.drawMyself(g);
            //根据方向，计算飞机新的坐标
            if(left){
                x-=speed;
            }
            if(right){
                x+=speed;
            }
            if(up){
                y-=speed;
            }
            if(down){
                y+=speed;
            }
        }else{
            return;
        }
    }
    public Plane(Image img, int x, int y, int speed) {
        super(img, x, y);
        this.speed=speed;
    }
}
