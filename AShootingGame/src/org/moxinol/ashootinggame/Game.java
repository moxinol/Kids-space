package org.moxinol.ashootinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 11/25/2017.
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;

    public final String TITLE ="Shoot!";
     private boolean running = false;
     private  Thread thread;
     private BufferedImage backgroundImage = null;
     private BufferedImage image = null;
     private BufferedImage spriteSheet = null;

     private Player p;

     public void init(){

         ImageLoader loader = new ImageLoader();

         try {
             backgroundImage = loader.loadImage("/background.png");
         }catch (IOException e){
           e.printStackTrace();
           System.exit(1);
         }

         try {
             p = new Player(200,200,this);
         } catch (IOException e) {
             e.printStackTrace();
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
           // System.out.println("WORKING!");
        }
       stop();
    }

    private void tick(){
     p.tick();
    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        p.render(g);
        g.dispose();
        bs.show();

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
