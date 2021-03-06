package org.moxinol.ashootinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 11/25/2017.
 */
public class Game extends Canvas implements Runnable {

    //FIELDS
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;

    public final String TITLE ="Shoot!";
    private boolean running = false;
    private  Thread thread;
    private BufferedImage backgroundImage = null;
    private BufferedImage image = null;
    private BufferedImage spriteSheet = null;
    private boolean isShooting = false;
    private Font f = new Font("Verdana",Font.BOLD,12);
    private Player p;
    private Controller c;
    private int score = 0;
    private int numberOfEnemies = 5;
    private Bullet b;

    //INIT THE GAME
    public void init(){
        requestFocus();
        ImageLoader loader = new ImageLoader();

        try {
            backgroundImage = loader.loadImage("/background.png");
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        addKeyListener(new KeyInput(this));

        try {
            p = new Player(WIDTH /2,HEIGHT /2 + HEIGHT / 4,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        c = new Controller(this);

        c.createEnemies(numberOfEnemies);




    }


    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            p.setVelY(-5);
        }
        else if (key == KeyEvent.VK_DOWN){
            p.setVelY(5);
        }
        else if (key == KeyEvent.VK_RIGHT){
            p.setVelX(5);
        }
        else if (key == KeyEvent.VK_LEFT){
            p.setVelX(-5);
        }else if (key == KeyEvent.VK_SPACE || !isShooting){
            try {

                c.addBullet(new Bullet(p.getX() + 12,p.getY()));
                //c.addBullet(new Bullet(p.getX(),p.getY(),this));
                // c.addBullet(new Bullet(p.getX()+ 22,p.getY(),this));
                isShooting = true;
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            p.setVelY(0);
        }
        else if (key == KeyEvent.VK_DOWN){
            p.setVelY(0);
        }
        else if (key == KeyEvent.VK_RIGHT){
            p.setVelX(0);
        }
        else if (key == KeyEvent.VK_LEFT){
            p.setVelX(0);
        }
        else if (key == KeyEvent.VK_SPACE ){
            isShooting = false;
        }

    }


    public synchronized void start(){
        if (running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();

    }
    public synchronized void stop(){
        if (!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }


    //GAME LOOP
    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running){
            long now = System.nanoTime();
            delta +=(now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates +" Ticks, Frames "+ frames);
                frames = 0;
                updates = 0;
            }

        }
        stop();
    }
    //UPDATE GAME
    private void tick(){
        if (c.getEnemiesSize() == 0){
            c.createEnemies(numberOfEnemies);
        }


        p.tick();
        c.tick();

        //CHECK COLLISSION BETWEEN ENEMIES AND PLAYER
        Rectangle pRec = p.getBounds();
        // Rectangle eRec = e.getBounds();
        for (int i = 0;i < c.getEnemiesSize();i++) {
            Enemy enemy = c.getEnemies().get(i);
            if (pRec.intersects(enemy.getBounds())){
                System.out.println("CRASH");
                enemy.setDead(true);
                continue;
            }
        }
        //CHECK COLISION BETWEEN ENEMIES AND PLAYER BULLETS
        for (int i = 0;i< c.getpBulletsSize();i++) {
            Bullet bullet = c.getpBullets().get(i);
            for (int j = 0;j < c.getEnemiesSize();j++) {
                Enemy enemy = c.getEnemies().get(j);
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    System.out.println("ENEMY HIT");
                    c.addExplosion(new Explosion(enemy.getX(),enemy.getY()));
                    i--;
                    j--;
                    c.removeBullet(bullet);
                    c.removeEnemy(enemy);
                    score++;

                }

            }
        }



    }


    //DRAW ALL ELEMENTS OF GAME
    private void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();



        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        paintInfo(g);
        p.render(g);
        // e.render(g);
        c.render(g);
        g.dispose();
        bs.show();

    }

    //DRAW SCREEN INFO
    public void paintInfo(Graphics g){
        g.setColor(Color.RED);
        g.setFont(f);
        g.drawString("Score "+ score,5,20);
    }





    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        game.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        game.setMinimumSize(new Dimension(WIDTH,HEIGHT));


        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

}
