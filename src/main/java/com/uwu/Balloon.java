package com.uwu;

import processing.core.PApplet;

public class Balloon extends Ball {
    PApplet p;

    Balloon(PApplet parent, float x, float y) {
        super(parent, x, y, 1, 10);

        p = parent;
    }
}
