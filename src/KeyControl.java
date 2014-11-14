import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by nicholascrowley on 11/8/14.
 */
public class KeyControl extends KeyAdapter {

    public int x, y;
    int speed;

    KeyControl(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void keyPressed(KeyEvent e, Plane plane) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                plane.move (-5,0);
                break;
            case KeyEvent.VK_RIGHT:
                plane.move (5,0);
                break;
            case KeyEvent.VK_UP:
                plane.move (0,-5);
                break;
            case KeyEvent.VK_DOWN:
                plane.move (0,5);
                break;


            default:
                if(e.getKeyChar() == ' ')
                    System.out.println("FIRE!!");

        }
    }
}