import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
//工具类
public class GameUtil {
    public static final int FRAME_WIDTH=500;
    public static final int FRAME_HIGHT=500;
    private GameUtil(){};
    public static Image getImage(String path){
        Image img=null;
        URL url=GameUtil.class.getClassLoader().getResource(path);
        try {
            img= ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
