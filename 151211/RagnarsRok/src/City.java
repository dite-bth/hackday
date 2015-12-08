/**
 * Created by Linus on 2015-12-08.
 */
import org.newdawn.slick.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class City {

    private boolean shieldActive;
    private long shieldActivationTime;

    private  int direction;

    /** Sprites **/
    private SpriteSheet citySprite;
    private SpriteSheet cityShieldSprite;

    /** Animations **/
    private Animation cityAnimation;
    private Animation cityShieldAnimation;

    public City(int direction){

        //Initialize city
        this.direction = direction;
        this.shieldActive = false;
        try {
            citySprite = new SpriteSheet("/img/city.png", 128, 256);
            cityAnimation = new Animation(citySprite, 1);
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public void activateCityShield(long time){
        if(!shieldActive){
            shieldActivationTime = time;
            shieldActive = true;
        }
    }

    public void animateCity(GameContainer gc, Graphics g, long time){
        //Check if shield is active and reset after 5 sec
        if(shieldActive){
            if(time - shieldActivationTime > 3000){
                shieldActive = false;
            }
        }
        //Draw
        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            //City
            g.drawAnimation(cityAnimation, gc.getWidth()/2 - cityAnimation.getWidth()/2,
                    -cityAnimation.getHeight()/6);
            //Shield if active
            if (shieldActive) {
                g.setLineWidth(4f);
                g.drawArc(gc.getWidth()/2 - cityAnimation.getWidth()+16, cityAnimation.getHeight()/6, 200, 200,180,0);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            cityAnimation.getCurrentFrame().setRotation(180);
            g.drawAnimation(cityAnimation, gc.getWidth()/2 - cityAnimation.getWidth()/2,
                    gc.getHeight() - cityAnimation.getHeight() + cityAnimation.getHeight()/6);
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            cityAnimation.getCurrentFrame().setRotation(90);
            g.drawAnimation(cityAnimation, gc.getWidth() - cityAnimation.getWidth() - cityAnimation.getWidth()/5,
                    gc.getHeight()/2 - cityAnimation.getHeight()/2);
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            cityAnimation.getCurrentFrame().setRotation(270);
            g.drawAnimation(cityAnimation, cityAnimation.getWidth()/5,
                    gc.getHeight()/2 - cityAnimation.getHeight()/2);
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
    }
}


