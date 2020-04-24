package com.ddkphamtutorial;

import java.awt.*;

public class AIPaddle implements Paddle{
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    final double GRAVITY = 0.94;
    Ball b1 = new Ball();

    public AIPaddle(int player, Ball b){
        b1 = b;
        upAccel = false;
        downAccel = false;
        y = 210; // center paddle
        yVel = 0; // motionless paddle
        if(player==1){
            x = 20;
        } else {
            x = 660;
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
    }

    public void move() {

        y = b1.getY() - 40;

        if(y < 0){
            y = 0;
        } else if (y > 420){
            y = 420;
        }
    }

    public void setUpAccel(boolean input){
        upAccel = input;
    }

    public void setDownAccel(boolean input){
        downAccel = input;
    }

    public int getY() {
        return (int)y;
    }
}
