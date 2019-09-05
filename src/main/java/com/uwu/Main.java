package com.uwu;

import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    boolean spaceToggled;
    ArrayList<Balloon> balloons = new ArrayList<>();

    Cannon cannon;
    int level = 0;

    public static void main(String[] args) {
        PApplet.main("com.uwu.Main");
    }

    public void settings() {
        size(1000,700);
    }

    void resetLevels() {
        for(int i = 0; i < level; i++) {
            float rX = random(0, width);
            float rY = random(0, height);
            balloons.add(new Balloon(this, rX, rY));
        }

    }

    public void setup(){
        cannon = new Cannon(this, width/2, 150, 50);
    }

    public void draw(){
        clear();

        if(balloons.size() <= 0) {
            level++;
            resetLevels();
        }

        ArrayList<Balloon> removeBalloons = new ArrayList<>();
        ArrayList<CannonBall> removeCannonBalls = new ArrayList<>();
        for(Balloon balloon : balloons) {

            //collisions and remove
            for(CannonBall ball : cannon.cannonBalls) {
                if(balloon.detectCollision(ball)) {
                    removeBalloons.add(balloon);
                    removeCannonBalls.add(ball);
                }
            }
            balloon.updateLocation();
            balloon.display();
        }

        for (Balloon toRemove : removeBalloons) {
            balloons.remove(toRemove);
        }
        for (CannonBall toRemove : removeCannonBalls) {
            cannon.cannonBalls.remove(toRemove);
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
