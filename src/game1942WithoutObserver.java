/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *
 * @author Ilmi
 */
public class game1942WithoutObserver extends JApplet implements Runnable {

    private Thread thread;
    Image sea;
    Image myPlane;
    private BufferedImage bImg;
    static Graphics2D g2;
    int speed = 1, move = 0;
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    MyPlane m;

    static int w = 640, h = 480; // fixed size window game

    public static Explosion friendlyExplosion;
    public static Explosion enemyExplosion;


    boolean enemiesActive, gameOver;
   // Graphics2D g2 = null;
    ImageObserver observer;

    public void init() {
        
        setBackground(Color.white);
        Image island1, island2, island3;
        Image[] enemyExplosionImg = new Image[6];
        Image[] friendlyExplosionImg = new Image[7];

        try {
        //sea = getSprite("Resources/water.png");
        sea = ImageIO.read(new File("Resources/water.png"));
        island1 = ImageIO.read(new File("Resources/island1.png"));
        island2 = ImageIO.read(new File("Resources/island2.png"));
        island3 = ImageIO.read(new File("Resources/island3.png"));
        myPlane = ImageIO.read(new File("Resources/myplane_1.png"));
        
        enemiesActive = true;
        gameOver = false;
        observer = this;

        I1 = new Island(island1, 100, 100, speed, generator);
        I2 = new Island(island2, 200, 400, speed, generator);
        I3 = new Island(island3, 300, 200, speed, generator);

        //create Image[] for friendlyExplosionImg
        for(int i = 1; i <=7; i++){
            String s = String.format("Resources/explosion2_%s.png", i);
            friendlyExplosionImg[i-1] = ImageIO.read(new File(s));
        }

        //create Image[] for enemyExplosionImg
        for(int i = 1; i <=6; i++){
            String s = String.format("Resources/explosion1_%s.png", i);
            enemyExplosionImg[i-1] = ImageIO.read(new File(s));
        }

        friendlyExplosion = new Explosion(friendlyExplosionImg);
        enemyExplosion = new Explosion(enemyExplosionImg);


        m = new MyPlane(myPlane, 300, 360, 10);

            setFocusable(true);
        }
        catch (Exception e) {
            System.out.print("No resources are found");
        }
    }


    public class KeyControl extends KeyAdapter {

        public int x, y;
        int speed;

        KeyControl(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
           
                case KeyEvent.VK_LEFT:
                m.move (-5,0);
                    System.out.println("LEFT!!");
	        	break;
                case KeyEvent.VK_RIGHT:
                m.move (5,0);
                    System.out.println("RIGHT!!");
                break;
                case KeyEvent.VK_UP:
                    m.move (0,-5);
                    System.out.println("UP!!");
                    break;
                case KeyEvent.VK_DOWN:
                    m.move (0,5);
                    System.out.println("DOWN!!");
                    break;

          
                default:
                  if(e.getKeyChar() == ' ')
	        		System.out.println("FIRE!!");

            }
        }
    }

    public class MyPlane {

        KeyControl key;
        Image img;
        int x, y, speed, move = 0;
        int boom;

        MyPlane(Image img, int x, int y, int speed) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            key = new KeyControl(x, y, speed);
            boom = 0;

            addKeyListener(key);
        }

        public void draw(ImageObserver obs) {
            g2.drawImage(img, this.x, this.y, obs);
        }
        
        public void move (int x, int y) {
            this.x += x;
            this.y += y;
        }
    }
    public class Plane extends Vehicle {
        KeyControl key;
        Image img;
        int x, y, speed, move = 0;
        int boom;
        /*

         */
        Plane(Image img, int x, int y, int speed) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            key = new KeyControl(x, y, speed);
            boom = 0;

            addKeyListener(key);
        }


        public void update() {

        }



        void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }

         void move (int x, int y) {
            this.x += x;
            this.y += y;
        }
    }

    public void drawBackGroundWithTileImage() {
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, 
                        i * TileHeight + (move % TileHeight), TileWidth, 
                        TileHeight, this);
            }
        }
        move += speed;
    }

    public void drawDemo() {
        if (!gameOver) {
            drawBackGroundWithTileImage();
            I1.update();
            I2.update();
            I3.update();
            
            I1.draw(this);
            I2.draw(this);
            I3.draw(this);
            m.draw(this);
        }
    }

    public void paint(Graphics g) {
        if(bImg == null) {
            Dimension windowSize = getSize();
            bImg = (BufferedImage) createImage(windowSize.width,
                    windowSize.height);
            g2 = bImg.createGraphics();
        }
        drawDemo();
        g.drawImage(bImg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {
    	
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();  
          try {
                thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }

    public static void main(String argv[]) {
        final game1942WithoutObserver demo = new game1942WithoutObserver();
        demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }

}

