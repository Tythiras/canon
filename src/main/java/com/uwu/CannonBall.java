package com.uwu;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.CENTER;

public class CannonBall extends Ball {
    float size;
    float force = 20;
    float angle = 0;
    CannonBall(PApplet parent, float x, float y, float size, float angle) {
        super(parent, x, y, 50, size);
        this.size = size;
        this.health = 2;

        PVector direction = PVector.fromAngle(PApplet.radians(angle));

        this.applyAcceleration(direction.setMag(force / (size / 30)));
        this.rotation = direction.mag() % 90;
    }
    void updateLocation() {
        angle += 2;
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
