package org.moxinol.ashootinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

/**
 * Created by moxinol on 12/7/2017.
 */
public class Explosion {

    private double x,y;
    private BufferedImage[] explFrames =new BufferedImage[16];
    private boolean over = false;
    private ImageLoader loader = new ImageLoader();
    private BufferedImage image;
    private SpriteSheet ss;
    //private BufferedImage currentImage;
    //private double delay = 62;
    private int counter;
    private boolean done = false;
    private int frames = 16;




    public Explosion(double x,double y){
        this.x =x;
        this.y = y;

        try {
            image =loader.loadImage("/explosion.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ss = new SpriteSheet(image);

        explFrames[0] = ss.grabImage(0,0,32,32);
        explFrames[1] = ss.grabImage(32,0,32,32);
        explFrames[2] = ss.grabImage(64,0,32,32);
        explFrames[3] = ss.grabImage(96,0,32,32);
        explFrames[4] = ss.grabImage(0,33,32,32);
        explFrames[5] = ss.grabImage(32,32,32,32);
        explFrames[6] = ss.grabImage(64,32,32,32);
        explFrames[7] = ss.grabImage(96,32,32,32);
        explFrames[8] = ss.grabImage(0,64,32,32);
        explFrames[9] = ss.grabImage(32,64,32,32);
        explFrames[10] = ss.grabImage(64,64,32,32);
        explFrames[11] = ss.grabImage(96,64,32,32);
        explFrames[12] = ss.grabImage(0,96,32,32);
        explFrames[13] = ss.grabImage(32,96,32,32);
        explFrames[14] = ss.grabImage(64,96,32,32);
        explFrames[15] = ss.grabImage(96,96,32,32);
         counter = 0;

        //currentImage = explFrames[0];

    }

    //UPDATE EXPLOSION
    public void tick(){

       // anim.runAnimation();
    }

    //DRAW EXPLOSION
    public void render(Graphics g){
        // anim.drawAnimation(g,x,y);
if (!isDone()) {
    if (counter < frames) {
     g.drawImage(explFrames[counter],(int)x,(int)y,null);
     counter++;
    }else {
        done = true;
    }
}



    }



    public boolean isDone(){
        return done;
    }

}
