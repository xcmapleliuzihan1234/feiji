package com.ahstu.ak;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
��Ϸ������
 */
public class myGameFrame extends Frame {
    Image planeImg=gameUtil.getImages("image/plane.jpg");
    Image bg=gameUtil.getImages("image/bg.jpg");

     plane p1=new plane(planeImg,240,400,10);
     boolean left,right,up,down;
    @Override
    public void paint(Graphics g) {//g�൱�ڻ���
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
    //��ʼ������
    public void launchFrame(){
        this.setTitle("������ɻ�");
        setVisible(true);//�����Ƿ�ɼ�
        setSize(500,500);//���ڴ�С
        setLocation(400,150);//����λ��
        this.addWindowListener(new WindowAdapter() {//�������ڣ����˳�
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new paintThread().start();
       this.addKeyListener(new keyMonitor());//��������
    }

     class paintThread extends Thread{//���˫���弼���������Ļ��˸
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
    //�ڲ��࣬���̿��Ʒɻ�
    class keyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("����"+e.getKeyCode());
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
            System.out.println("̧��"+e.getKeyCode());
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
