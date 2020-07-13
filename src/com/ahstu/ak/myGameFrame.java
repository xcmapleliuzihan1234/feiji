package com.ahstu.ak;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
��Ϸ������
 */
public class myGameFrame extends Frame {
    Image plane=gameUtil.getImages("image/plane.jpg");
    Image bg=gameUtil.getImages("image/bg.jpg");
     int planex=240,planey=400;
     int count=0;
    @Override
    public void paint(Graphics g) {//g�൱�ڻ���
        System.out.println("���ƴ��ڴ���"+count);
        count++;
        g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HIGHT,null);
        g.drawImage(plane,planex,planey,32,33,null);
       planex+=2;planey-=2;
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
    }
     class paintThread extends Thread{//���˫���弼��
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
