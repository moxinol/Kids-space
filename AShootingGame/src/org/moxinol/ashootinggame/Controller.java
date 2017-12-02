package org.moxinol.ashootinggame;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Controller {
    private ArrayList<Bullet> pBullets = new ArrayList<Bullet>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    Bullet tempBullet;
    Enemy tempEnemy;
    private Game game;

    public Controller(Game game){
        this.game = game;


    }

    public void tick(){
        tickBullets();
        tickEnemy();
    }

    public void render(Graphics g){
        renderBullets(g);
        renderEnemies(g);
    }

////////BULLETS/////////


    //ADD BULLET
    public void addBullet(Bullet bullet){
        pBullets.add(bullet);
    }

    //REMOVE BULLET
    public void removeBullet(Bullet bullet){
        pBullets.remove(bullet);
    }

    //DRAW BULLETS
    public void renderBullets(Graphics g){
        for (int i = 0; i< pBullets.size(); i++){
            tempBullet = pBullets.get(i);
            tempBullet.render(g);
        }
    }

    //UPDATE BULLETS
    public void tickBullets(){
        for (int i = 0; i< pBullets.size(); i++){
            tempBullet = pBullets.get(i);
            if (tempBullet.getY() < 0){
                pBullets.remove(tempBullet);
            }
            tempBullet.tick();
        }
    }
///////////7ENEMIES////////////

    //ADD ENEMIES
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    //REMOVE ENEMIES
    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

    //DRAW ENEMIES
    public void renderEnemies(Graphics g){
        for (int i =0;i< enemies.size();i++){
            tempEnemy = enemies.get(i);
            tempEnemy.render(g);
        }
    }

    //UPDATE ENEMIES
    public void tickEnemy(){
        for (int i =0;i< enemies.size();i++){
            tempEnemy = enemies.get(i);
            if (tempEnemy.getY() > 640){
                tempEnemy.setY(Math.floor(Math.random() * 640));
            }
            tempEnemy.tick();
        }
    }

    //CREATE ENEMIES
    public void createEnemies(int nEnemies){
        for (int i = 0;i < nEnemies;i++){
            double  randomPos = Math.floor(Math.random() * 640);

            try {
                addEnemy(new Enemy(randomPos,0,game));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //GETTERS AND SETTERS
    //GET SIZE OF ENEMIES LIST
    public int getEnemiesSize(){
        return enemies.size();
    }
    //RETURNS ENEMY LIST
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    //RETURN BULLET LIST
    public ArrayList<Bullet> getpBullets(){
        return pBullets;
    }
    //RETURN BULLETS LIST SIZE
    public int getpBulletsSize(){
        return pBullets.size();
    }
}
