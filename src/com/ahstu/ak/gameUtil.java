package com.ahstu.ak;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class gameUtil {
    public static Image getImages(String path) {
        BufferedImage img = null;
        URL u = gameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}

