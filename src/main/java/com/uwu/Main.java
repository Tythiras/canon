package com.uwu;

import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    boolean spaceToggled;
    ArrayList<Balloon> balloons = new ArrayList<>();

    Cannon cannon;

    public static void main(String[] args) {
        PApplet.main("com.uwu.Main");
    }

    public void settings(){
        size(1000,700);
    }

    public void setup(){
        cannon = new Cannon(this, width/2, 150, 50);
        balloons.add(new Balloon(this, width/2, height/2));
    }

    public void draw(){
        clear();
        for(Balloon balloon : balloons) {
            balloon.updateLocation();
            balloon.display();
        }
        cannon.updateLocation();
        cannon.display();
    }

    public void keyReleased() {
        if(keyCode==32) {
            spaceToggled = false;
        } else if(keyCode==39) {
            cannon.rifGoingRight = false;
            cannon.goingRight = false;
        } else if(keyCode==37) {
            cannon.rifGoingLeft = false;
            cannon.goingLeft = false;
        }
    }
    public void keyPressed() {
        if(keyCode==32) {
            spaceToggled = true;
        } else if(keyCode==39) {
            if(spaceToggled) {
                cannon.rifGoingRight = true;
            } else {
                cannon.goingRight = true;
            }
        } else if(keyCode==37) {
            if(spaceToggled) {
                cannon.rifGoingLeft = true;
            } else {
                cannon.goingLeft = true;
            }
        } else if(keyCode==10) {
            cannon.shoot();
        }
    }


}
