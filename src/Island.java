import com.sun.xml.internal.rngom.parse.host.Base;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * method: Draw is inherited from the base abstract class
 */
public class Island extends BaseAC{

Random gen;

    Island(Image img, int x, int y, int speed, Random gen) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.gen = gen;
    }

    public void update() {
        y += speed;
        if (y >= game1942WithoutObserver.h) {
            y = -100;
            x = Math.abs(gen.nextInt() % (game1942WithoutObserver.w - 30));
        }
    }



}