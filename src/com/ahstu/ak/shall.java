package com.ahstu.ak;
import java.awt.*;
public class shall extends GameObject {
    double degree;//×Óµ¯½Ç¶È
    public shall(){
        x=200;
        y=200;
        degree=Math.random()*2*Math.PI;
        width=5;
        height=5;
        speed=3;
    }
    @Override
    public void drawMyself(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);;
        if (y>Constant.GAME_HEIGHT-this.height||y<0){
            degree=-degree;
        }if (x>Constant.GAME_WIDTH-this.width||x<0){
            degree=Math.PI-degree;
        }
        g.setColor(c);
    }
}
