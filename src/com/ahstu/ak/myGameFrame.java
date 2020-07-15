package com.ahstu.ak;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
游戏主窗口
 */
public class myGameFrame extends Frame {
    Image planeImg=gameUtil.getImages("image/plane.jpg");
    Image bg=gameUtil.getImages("image/bg.jpg");
     plane p1=new plane(planeImg,240,400,7);
        shall[] shalls=new shall[20];
    @Override
    public void paint(Graphics g) {//g相当于画笔
        g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);
        p1.drawMyself(g);
        for (int i=0;i<shalls.length;i++){
            shalls[i].drawMyself(g);
            //碰撞检测
            boolean peng=shalls[i].getRec().intersects(p1.getRec());
            if(peng){
              //  System.out.println("创创打好了");
                p1.live=false;
            }
    }
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
       this.addKeyListener(new keyMonitor());//启动按键
        for (int i=0;i<50;i++){
            shalls[i]=new shall();
        }
    }
     class paintThread extends Thread{//添加双缓冲技术，解决屏幕闪烁
        public void run(){
            while (true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //内部类，键盘控制飞机
    class keyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            p1.addDirction(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            p1.minusDirction(e);
        }
    }
    private Image offScreenImage = null;
    public void update(Graphics g){
        if(offScreenImage==null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }
    public static void main(String[] args) {
        myGameFrame gameFrame=new myGameFrame();
        gameFrame.launchFrame();
    }
}
