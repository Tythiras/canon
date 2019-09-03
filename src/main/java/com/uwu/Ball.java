package com.uwu;

import processing.core.PApplet;
import processing.core.PVector;

public class Ball {
    PApplet p;

    float gravity = (float) 3;

    boolean hasGravity = true;

    PVector location;
    PVector velocity;
    PVector acceleration;

    float rotation = 0;
    float health = 1;
    float mass;
    float radius;
    Ball(PApplet parent, float x, float y, float mass, float radius) {
        location = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        this.mass = mass;
        this.radius = radius;
        p = parent;
    }

    void applyAcceleration(PVector acc) {
        acceleration.add(acc);
    }
    void updateLocation() {
        //gravity
        if(hasGravity) {

            acceleration.y = acceleration.y + (gravity / mass);
        }

        //movement engine
        velocity.add(acceleration);
        location.add(velocity);

        if(location.x+radius>p.width) {
            location.x = p.width-radius;
            velocity.x = velocity.x * -1;
            health -= 1;
        }
        if(location.x-radius<0) {
            location.x = radius;
            velocity.x = velocity.x * -1;
            health -= 1;
        }
        if(location.y+radius>p.height) {
            location.y = p.height-radius;
            velocity.y = velocity.y * -1;
            health -= 1;
        }
        if(location.y-radius<0) {
            location.y = radius;
            velocity.y = velocity.y * -1;
            health -= 1;
        }

        //reset acceleration
        acceleration.mult(0);
    }
    void display() {
        //draw ball
        p.fill(255);
        p.ellipse(location.x, location.y, radius*2, radius*2);
    }


}
