import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by nicholascrowley on 11/13/14.
 */
public abstract class Vehicle extends BaseAC {
    int boom, count;
    ImageObserver observer;
    abstract void update();
    abstract void draw(Graphics g, ImageObserver obs);
    abstract void move(int x, int y);

}
