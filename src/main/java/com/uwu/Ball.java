package com.uwu;

import processing.core.PApplet;
import processing.core.PVector;

public class Ball {
    float gravity = (float) 9.82;

    boolean hasGravity = true;
    PApplet p;

    PVector location;
    PVector velocity;
    PVector acceleration;


    float mass;
    float radius;
    float density;
    Ball(PApplet parent, float x, float y, float mass) {
        location = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        this.mass = mass;
        this.radius = mass/2;
        p = parent;
    }

    void applyAcceleration(PVector acc) {
        acceleration.add(acc);
    }
    void updateLocation() {
        if(hasGravity) {

            acceleration.y = acceleration.y + (gravity / mass);
        }

        velocity.add(acceleration);
        location.add(velocity);

        if(location.x+radius>p.width) {
            location.x = p.width-radius;
            velocity.x = velocity.x * -1;
        }
        if(location.x-radius<0) {
            location.x = radius;
            velocity.x = velocity.x * -1;
        }
        if(location.y+radius>p.height) {
            location.y = p.height-radius;
            velocity.y = velocity.y * -1;
        }
        if(location.y-radius<0) {
            location.y = radius;
            velocity.y = velocity.y * -1;
        }

        acceleration.mult(0);
    }
    void display() {
        p.ellipse(location.x, location.y, radius*2, radius*2);
    }


}
