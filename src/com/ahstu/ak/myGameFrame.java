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
     int planex=240,planey=400;
     int count=0;
    @Override
    public void paint(Graphics g) {//g相当于画笔
        System.out.println("绘制窗口次数"+count);
        count++;
        g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HIGHT,null);
        g.drawImage(plane,planex,planey,32,33,null);
       planex+=2;planey-=2;
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
        new paintThread().start();
    }
     class paintThread extends Thread{//添加双缓冲技术
        public void run(){
            while (true){
                repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Image offScreenImage = null;
    public void update(Graphics g){
        if(offScreenImage==null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HIGHT);
        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }
    public static void main(String[] args) {
        myGameFrame gameFrame=new myGameFrame();
        gameFrame.launchFrame();
    }
}
