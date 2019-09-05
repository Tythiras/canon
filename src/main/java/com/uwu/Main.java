package com.uwu;

import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    boolean spaceToggled;
    ArrayList<Balloon> balloons = new ArrayList<>();

    Cannon cannon;
    int level = 0;
    int points = 0;

    int infoTimer;
    String infoText;

    public static void main(String[] args) {
        PApplet.main("com.uwu.Main");
    }

    public void settings() {
        size(1000,700);
    }

    void generateLevel() {
        balloons.clear();
        cannon.cannonBalls.clear();
        for(int i = 0; i < level; i++) {
            float rX = random(50, width-50);
            float rY = random(50, height-100);
            balloons.add(new Balloon(this, rX, rY));
        }

    }

    public void setup(){
        cannon = new Cannon(this, width/2, 150, 50);
        infoText = "Hit the balloons before they pop!";
        infoTimer = 120;
    }

    public void draw(){
        clear();
        if(infoTimer==0) {

            textAlign(LEFT);
            textSize(32);
            text("Points: " + points, 20, 50);

            if (balloons.size() <= 0) {
                level++;
                generateLevel();
            }

            boolean reset = false;

            ArrayList<Balloon> removeBalloons = new ArrayList<>();
            ArrayList<CannonBall> removeCannonBalls = new ArrayList<>();
            for (Balloon balloon : balloons) {
                //detect it's health
                if(balloon.health<=0) {
                    //restart things
                    reset = true;
                }
                //collisions and remove
                for (CannonBall ball : cannon.cannonBalls) {
                    if (balloon.detectCollision(ball)) {
                        removeBalloons.add(balloon);
                        removeCannonBalls.add(ball);
                        points++;
                    }
                }


                //draw balloon
                balloon.updateLocation();
                balloon.display();
            }
            //remove things that has collided
            for (Balloon toRemove : removeBalloons) {
                balloons.remove(toRemove);
            }
            for (CannonBall toRemove : removeCannonBalls) {
                cannon.cannonBalls.remove(toRemove);
            }
            //for loop is done, restart things
            if(reset) {
                level = 1;
                points = 0;
                infoText = "A balloon popped!";
                infoTimer = 120;
                generateLevel();
            }

            cannon.updateLocation();
            cannon.display();
        } else {
            infoTimer--;
            textAlign(CENTER);
            textSize(40);
            text(infoText, width/2, height/3);
            textSize(24);
            text("Arrow left + Arrow Right -> Move cannon", width/2, (float) (1.5*(height/3)));
            text("Shift + (Arrow left + Arrow Right) -> Control angle", width/2, 2*(height/3));
            text("Enter -> Shoot ball", width/2, (float) (2.6*(height/3)));
        }
    }


    //controls
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
