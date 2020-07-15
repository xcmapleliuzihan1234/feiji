package com.ahstu.ak;

import java.awt.*;
import java.awt.event.KeyEvent;
public class plane extends GameObject {
    boolean left,right,up,down;
    boolean live=true;
    @Override
    public void drawMyself(Graphics g) {
        if (live) {
            super.drawMyself(g);
            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
        }
        //·É»ú·ÉÐÐµÄ¹ì¼£
     //   x+=speed;
    }
   public void addDirction(KeyEvent e){
       switch (e.getKeyCode()){
           case KeyEvent.VK_LEFT : left=true;
               break;
           case KeyEvent.VK_RIGHT: right=true;
               break;
           case KeyEvent.VK_UP: up=true;
               break;
           case KeyEvent.VK_DOWN: down=true;
               break;
       }
   }
   public void minusDirction(KeyEvent e){
       switch (e.getKeyCode()){
           case KeyEvent.VK_LEFT : left=false;
               break;
           case KeyEvent.VK_RIGHT: right=false;
               break;
           case KeyEvent.VK_UP: up=false;
               break;
           case KeyEvent.VK_DOWN: down=false;
               break;
       }
   }
    public plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }
}
