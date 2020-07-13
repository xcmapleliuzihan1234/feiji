package com.ahstu.ak;

import java.awt.*;

public class GameObject {
    Image img;
    int x,y;
    int speed;
    int width,height;

    public GameObject(Image img, int x, int y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
    public void drawMyself(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
    public  Rectangle getRec(){//Åö×²¼ì²â
        return new Rectangle(x,y,width,height);
    }
}
