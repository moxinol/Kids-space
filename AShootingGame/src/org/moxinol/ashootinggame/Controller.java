package org.moxinol.ashootinggame;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by moxinol on 12/2/2017.
 */
public class Controller {
    private LinkedList<Bullet> eBullets = new LinkedList<Bullet>();

    Bullet tempBullet;

    public Controller(){

    }

    public void tick(){
        for (int i =0;i< eBullets.size();i++){
            tempBullet = eBullets.get(i);
            if (tempBullet.getY() < 0){
                eBullets.remove(tempBullet);
            }
            tempBullet.tick();
        }
    }

    public void render(Graphics g){
        for (int i =0;i< eBullets.size();i++){
            tempBullet = eBullets.get(i);
            tempBullet.render(g);
        }
    }

    public void addBullet(Bullet bullet){
        eBullets.add(bullet);
    }

    public void removeBullet(Bullet bullet){
        eBullets.remove(bullet);
    }


}
