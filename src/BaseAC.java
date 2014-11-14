import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Created by nicholascrowley on 11/13/14.
 */
public abstract class BaseAC {
    int x, y, speed;
    Image img;

    abstract void update();
    public void draw(ImageObserver obs) {
        game1942WithoutObserver.g2.drawImage(img, this.x, this.y, obs);
    }
}
