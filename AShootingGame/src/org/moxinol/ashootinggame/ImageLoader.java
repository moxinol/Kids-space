package org.moxinol.ashootinggame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by moxinol on 11/25/2017.
 */
public class ImageLoader {
private BufferedImage image;

  //loads image
    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(ImageLoader.class.getResource(path));
          return image;

    }
}
