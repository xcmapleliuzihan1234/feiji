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

     plane p1=new plane(planeImg,240,400,10);
     boolean left,right,up,down;
    @Override
    public void paint(Graphics g) {//g相当于画笔
        g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);
        p1.drawMyself(g);
        if(left){
            p1.x-=p1.speed;
        }if(right){
            p1.x+=p1.speed;
        }if(up){
            p1.y-=p1.speed;
        }if(down){
            p1.y+=p1.speed;
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
            System.out.println("按下"+e.getKeyCode());
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                left=true;
            } if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                right=true;
            } if(e.getKeyCode()==KeyEvent.VK_UP){
                up=true;
            } if(e.getKeyCode()==KeyEvent.VK_DOWN){
                down=true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("抬起"+e.getKeyCode());
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                left=false;
            } if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                right=false;
            } if(e.getKeyCode()==KeyEvent.VK_UP){
                up=false;
            } if(e.getKeyCode()==KeyEvent.VK_DOWN){
                down=false;
            }
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
