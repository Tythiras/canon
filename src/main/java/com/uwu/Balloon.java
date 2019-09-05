package com.uwu;

import processing.core.PApplet;

public class Balloon extends Ball {
    PApplet p;

    Balloon(PApplet parent, float x, float y) {
        super(parent, x, y, 1, 10);
        this.health = 5;
        p = parent;
    }

    void display() {
        //draw ballon
        p.fill(255-health*2*25, health*2*25, 0);
        p.ellipse(location.x, location.y, radius*2, radius*2);
    }
}
