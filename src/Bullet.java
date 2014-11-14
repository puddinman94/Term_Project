import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

/**
 * Created by nicholascrowley on 11/13/14.
 */
public class Bullet extends BulletAC {


    public Bullet(Image img, int speed, int x, int y){
        this.img = img;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }
    boolean collision(int x, int y) {
        return false;
    }

    void update() {

    }

    void draw(Graphics g, ImageObserver obs) {

    }
}
