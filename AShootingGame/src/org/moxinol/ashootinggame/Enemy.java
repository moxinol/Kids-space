package org.moxinol.ashootinggame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Enemy {

    //FIELDS
    private double x;
    private double y;
    private BufferedImage enemyImage;
    private ImageLoader loader;
    private int velY = 3;
    private boolean dead = false;


    //CONSTRUCTOR
    public Enemy(double x,double y,Game game) throws IOException {
        this.x = x;
        this.y = y;

        loader = new ImageLoader();
        enemyImage = loader.loadImage("/enemy_ship1.png");


    }
    //UPDATE ENEMY
    public void tick(){
        y += velY;

        if (y > Game.HEIGHT){
            y =0;
            x = Math.floor(Math.random() * 640);
        }
    }
    //DRAW ENEMY
    public void render(Graphics g){
        g.drawImage(enemyImage,(int)x,(int)y,null);

    }

//GETTERS AND SETTERS

    public int getVelY(){
        return velY;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public double getY(){
        return y;
    }


    public double getX() {
        return x;
    }

    public void setY(double y){
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    public boolean isDead(){
        return dead;
    }
    public void setDead(boolean dead){
        this.dead = dead;
    }

}
