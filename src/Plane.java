import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Component.*;

/**
 * Created by nicholascrowley on 11/13/14.
 */
public class Plane {
    KeyControl key;
    Image img;
    int x, y, speed, move = 0;
    int boom;


    Plane(Image img, int x, int y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        key = new KeyControl(x, y, speed);
        boom = 0;
        //TODO: FIND OUT WY KEY LISTENER NO WORK
//       addKeyListener(key);
    }


    public void update() {

    }

    void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, obs);
    }

    public void move (int x, int y) {
        this.x += x;
        this.y += y;
    }
}
