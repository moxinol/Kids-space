package org.moxinol.ashootinggame;

import java.awt.*;
import java.awt.font.GraphicAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 11/25/2017.
 */
public class Player {

    private double x;
    private double y;
    private int velX =0;
    private int velY = 0;

    private ImageLoader loader;
    private BufferedImage playerShip;

    public Player(double x,double y,Game game) throws IOException {
        this.x = x;
        this.y = y;

        loader = new ImageLoader();
        playerShip = loader.loadImage("/ship32x32.png");

        //SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
       //playerShip = ss.grabImage(x,y,32,32)
    }

    public void tick(){
     x += velX;
     y += velY;

     if (x <= 0){
         x = 0;
     }
     if (x >= Game.WIDTH - 20){
         x = Game.WIDTH - 20;
     }
     if (y <= 0){
         y = 0;
     }
     if (y >= Game.HEIGHT - 20){
         y = Game.HEIGHT - 20 ;
     }
    }
    public void render(Graphics g){
     g.drawImage(playerShip,(int)x,(int)y,null);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

public int getVelX(){
        return velX;
}
public int getVelY(){
    return velY;
}

public void setVelX(int velX){
    this.velX = velX;
}
public void setVelY(int velY){
    this.velY = velY;
}


}
