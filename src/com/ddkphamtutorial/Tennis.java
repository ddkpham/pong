package com.ddkphamtutorial;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    boolean gameStarted;
    Graphics gfx;
    Image img;
    int p1Score;
    int p2Score;

    public void init(){
        gameStarted = false;
        p1Score = 0;
        p2Score = 0;
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);
        // run game
        p1  = new HumanPaddle(1);
        b1 = new Ball();
        p2 = new AIPaddle(2, b1);
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        // Create black backdrop
        gfx.setColor(Color.black);
        gfx.fillRect(0,0, WIDTH, HEIGHT);
        if(b1.getX() < -10 || b1.getX() > 710){
            gfx.setColor(Color.red);
            gfx.drawString("GAME OVER", 300, 250);
        }
        if(!gameStarted){
            gfx.setColor(Color.white);
            gfx.drawString("Press Enter to Start Game", 280, 130);
        }
        gfx.setColor(Color.white);
        gfx.drawString("SCORE", 320, 50);
        gfx.drawString(Integer.toString(p1Score), 320, 70);
        gfx.drawString(Integer.toString(p2Score), 350, 70);
        p1.draw(gfx);
        b1.draw(gfx);
        p2.draw(gfx);
        g.drawImage(img, 0, 0, this);
    }

    public void update(Graphics g){
        paint(g);
    }

    // Implements Runnable
    public void run(){
        while(true){

            if(gameStarted){
                p1.move();
                b1.move();
                p2.move();
                b1.checkPaddleCollision(p1,p2);
                repaint();
            }
            try{
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //Implements KeyListener
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("MOVING UP");
            p1.setUpAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p1.setUpAccel(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(false);
        }
    }
    public void keyTyped(KeyEvent arg0){

    }
}
