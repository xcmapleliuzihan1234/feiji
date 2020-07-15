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
     plane p1=new plane(planeImg,240,400,7);
        shall[] shalls=new shall[20];
    @Override
    public void paint(Graphics g) {//g�൱�ڻ���
        g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);
        p1.drawMyself(g);
        for (int i=0;i<shalls.length;i++){
            shalls[i].drawMyself(g);
            //��ײ���
            boolean peng=shalls[i].getRec().intersects(p1.getRec());
            if(peng){
              //  System.out.println("���������");
                p1.live=false;
            }
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
        for (int i=0;i<50;i++){
            shalls[i]=new shall();
        }
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
