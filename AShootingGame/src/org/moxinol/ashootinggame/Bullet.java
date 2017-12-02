package org.moxinol.ashootinggame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Bullet {

private double x;
private double y;
private ImageLoader loader = new ImageLoader();
private BufferedImage bulletImage;

public Bullet(double x,double y,Game game) throws IOException {
    this.x = x;
    this.y = y;


    bulletImage = loader.loadImage("/laser_red.png");
}
public void tick(){
y -= 5;
}
public void render(Graphics g){
    g.drawImage(bulletImage,(int)x,(int)y,null);
}

    public double getY(){
        return y;
    }

}
