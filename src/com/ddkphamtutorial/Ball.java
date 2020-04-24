package com.ddkphamtutorial;

import java.awt.*;

public class Ball {
    double xVel, yVel, x, y;

    public Ball(){
        x = 350;
        y = 350;
        xVel = getRandomSpeed() * getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
    }
    public double getRandomSpeed(){
        return (Math.random() * 3 + 2);
    }
    public int getRandomDirection(){
        int rand = (int) (Math.random() * 2);
        if(rand == 1){
            return 1;
        } else {
            return -1;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        // subtract by 10 to center circle
        g.fillOval((int)x - 10, (int)y - 10, 20, 20);
    }

    public void checkPaddleCollision(Paddle p1, Paddle p2){
        if(x <= 50){
            if(y >= p1.getY() && y <= p1.getY() + 80){
                xVel = -xVel;
            }
        } else if(x >= 650){
            if(y >= p2.getY() && y <= p2.getY() + 80){
                xVel = -xVel;
            }
        }
    }

    public void move(){
        x += xVel;
        y += yVel;
        // hits top of screen
        if(y < 10){
            yVel = -yVel;
        }
        if(y > 490){
            yVel = -yVel;
        }
    }

    public double getX() {
        return (int)x;
    }

    public double getY() {
        return (int)y;
    }
}
