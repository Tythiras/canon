package com.uwu;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.CENTER;

public class CannonBall extends Ball {
    float size;
    float force = 10;
    float angle = 0;
    CannonBall(PApplet parent, float x, float y, float size, float angle) {
        super(parent, x, y, size, size/2);
        this.size = size;
        this.health = 10;

        PVector direction = PVector.fromAngle(PApplet.radians(angle));

        this.applyAcceleration(direction.setMag(force));
        this.rotation = direction.mag() % 90;
    }
    void updateLocation() {
        angle += 1;
        super.updateLocation();
    }
    void display() {
        super.display();
        p.pushMatrix();
        p.translate(location.x, location.y);
        p.rotate(PApplet.radians(angle));

        p.rectMode(CENTER);
        p.fill((float) (health*25.5));
        p.rect(0, 0, size/2, size/2);
        p.fill(255);
        p.popMatrix();
    }

}
