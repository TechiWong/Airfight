import java.awt.*;

public class Shell extends GameObject{
    double degree;

    public Shell() {
        degree = Math.random()*Math.PI*2;
        x=100;
        y=100;
        width=10;
        height=10;
        speed=4;
    }
    @Override
    public void drawMyself(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
        //炮弹沿着任意角度飞行
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);
        //边界碰撞回弹
        if(y>GameUtil.FRAME_HIGHT||y<30){
            degree=-degree;
        }
        if(x>GameUtil.FRAME_WIDTH-10||x<0){
            degree=Math.PI-degree;
        }
        g.setColor(c);
    }
}
