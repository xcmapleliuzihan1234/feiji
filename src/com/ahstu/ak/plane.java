package com.ahstu.ak;

import java.awt.*;

public class plane extends GameObject {
    @Override
    public void drawMyself(Graphics g) {
        super.drawMyself(g);
        //�ɻ����еĹ켣
     //   x+=speed;
    }

    public plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }
}
