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
    private  double y;

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

    }
    public void render(Graphics g){
     g.drawImage(playerShip,(int)x,(int)y,null);
    }
}
