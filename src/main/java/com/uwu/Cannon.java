package com.uwu;

import processing.core.PApplet;

import java.util.ArrayList;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class Cannon {
    PApplet p;
    ArrayList<CannonBall> cannonBalls = new ArrayList<>();

    float xLocation;
    float rifleAngle = 120;


    float riffleHeight = 150;
    float riffleWidth = 20;
    float width;
    float height;

    boolean goingRight;
    boolean goingLeft;
    boolean rifGoingRight;
    boolean rifGoingLeft;
    Cannon(PApplet parent, float x, float width, float height) {
        p = parent;
        xLocation = x;
        this.width = width;
        this.height = height;
    }

    void shoot() {
        float angle = rifleAngle + 90;
        //corner of riffle
        float x = xLocation-width/2+20;
        float y = p.height-height+15;


        x += Math.cos(Math.toRadians(angle)) * riffleHeight;
        y += Math.sin(PApplet.radians(angle)) * riffleHeight;
        CannonBall ball = new CannonBall(p, x, y, 50, angle);
        cannonBalls.add(ball);
    }
    void updateLocation() {
        if(goingRight) {
            xLocation += 5;
        }
        if(goingLeft) {
            xLocation -= 5;
        }
        if(rifGoingRight) {
            rifleAngle+=1;
        }
        if(rifGoingLeft) {
            rifleAngle-=1;
        }

        //to remove array
        ArrayList<CannonBall> toRemove = new ArrayList<>();
        //update cannonballs
        for(CannonBall ball : cannonBalls) {
            ball.updateLocation();
            if(ball.health<=0) {
                toRemove.add(ball);
            }
        }
        for(CannonBall ball : toRemove) {
            cannonBalls.remove(ball);
        }
    }
    void display() {
        p.pushMatrix();
        p.translate(xLocation, p.height - height /2);
        //main chasis
        p.rectMode(CENTER);
        p.rect(0, 0, width, height );

        //rifle
        p.rectMode(CORNER);
        p.translate(-width/2+20, -height/2+15);
        p.rotate(p.radians(rifleAngle));
        p.rect(-10,0 , riffleWidth, riffleHeight);

        p.popMatrix();

        //balls

        //update cannonballs
        for(CannonBall ball : cannonBalls) {
            ball.display();
        }
    }
}
