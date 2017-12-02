package org.moxinol.ashootinggame;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Controller {
    private LinkedList<Bullet> eBullets = new LinkedList<Bullet>();
    private LinkedList<Enemy> enemies = new LinkedList<Enemy>();

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

    public void addBullet(Bullet bullet){
        eBullets.add(bullet);
    }

    public void removeBullet(Bullet bullet){
        eBullets.remove(bullet);
    }

    public void renderBullets(Graphics g){
        for (int i =0;i< eBullets.size();i++){
            tempBullet = eBullets.get(i);
            tempBullet.render(g);
        }
    }

    public void tickBullets(){
        for (int i =0;i< eBullets.size();i++){
            tempBullet = eBullets.get(i);
            if (tempBullet.getY() < 0){
                eBullets.remove(tempBullet);
            }
            tempBullet.tick();
        }
    }
///////////7ENEMIES////////////
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }


    public void renderEnemies(Graphics g){
        for (int i =0;i< enemies.size();i++){
            tempEnemy = enemies.get(i);
            tempEnemy.render(g);
        }
    }

    public void tickEnemy(){
        for (int i =0;i< enemies.size();i++){
            tempEnemy = enemies.get(i);
            if (tempEnemy.getY() > 640){
                tempEnemy.setY(Math.floor(Math.random() * 640));
            }
            tempEnemy.tick();
        }
    }

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

    public int getEnemies(){
        return enemies.size();
    }



}
