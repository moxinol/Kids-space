package org.moxinol.ashootinggame;

import java.awt.image.BufferedImage;

/**
 * Created by moxinol on 11/25/2017.
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image ){
        this.image = image;
    }

    public BufferedImage grabImage(int x,int y,int width,int heigth){
        BufferedImage img = image.getSubimage(x,y,width,heigth);
        return img;
    }

}
