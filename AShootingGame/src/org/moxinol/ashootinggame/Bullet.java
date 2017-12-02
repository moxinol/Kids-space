package org.moxinol.ashootinggame;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Bullet {

    //FIELDS
    private double x;
    private double y;
    private ImageLoader loader = new ImageLoader();
    private BufferedImage bulletImage;

    //CONSTRUCTOR
    public Bullet(double x,double y) throws IOException {
        this.x = x;
        this.y = y;

        bulletImage = loader.loadImage("/laser_red.png");

    }

    //UPDATE BULLET
    public void tick(){
        y -= 5;
    }

    //DRAW BULLET
    public void render(Graphics g){
        g.drawImage(bulletImage,(int)x,(int)y,null);
    }

    //GETTERS AND SETTERS
    public double getY(){
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,8,34);
    }


}
