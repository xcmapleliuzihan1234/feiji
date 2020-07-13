package com.ahstu.ak;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
游戏主窗口
 */
public class myGameFrame extends Frame {
    Image plane=gameUtil.getImages("image/plane.jpg");
    Image bg=gameUtil.getImages("image/bg.jpg");

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg,0,0,500,500,null);
        g.drawImage(plane,240,400,32,33,null);
    }

    //初始化窗口
    public void launchFrame(){
        this.setTitle("创创打飞机");
        setVisible(true);//窗口是否可见
        setSize(500,500);//窗口大小
        setLocation(400,150);//窗口位置
        this.addWindowListener(new WindowAdapter() {//监听窗口：可退出
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        myGameFrame gameFrame=new myGameFrame();
        gameFrame.launchFrame();
    }
}
