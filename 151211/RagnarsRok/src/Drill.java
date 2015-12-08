/**
 * Created by Linus on 2015-12-07.
 */
import org.newdawn.slick.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Drill {

    /** Sprites **/
    private SpriteSheet drillTipSprite;
    private SpriteSheet drillSprite;

    /** Animations **/
    private Animation drillTipAnimation;
    private Animation drillAnimation;

    private ArrayList drill;

    private int x;
    private int y;
    private  int direction;

    public Drill(int direction, int x, int y){

        //Initialize drill
        this.x = x;
        this.y = y;

        try {
            drillTipSprite = new SpriteSheet("/img/drill_tip.png", 32, 32);
            drillSprite = new SpriteSheet("/img/drill_part.png", 32, 32);

            this.direction = direction;

            drillTipAnimation = new Animation(drillTipSprite, 10);
            drillAnimation = new Animation(drillSprite, 10);

            drill = new ArrayList<Animation>();
            drill.add(drillTipAnimation);
            drill.add(drillAnimation);


        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public void drawDrill(Graphics g){
        int offset = 0;
        for(int i = drill.size()-1; i >=0 ; i--){
            if(direction == GameStatics.PLAYER_ORIENTATION_N) {
                g.drawAnimation((Animation)drill.get(i), x, y + offset);
                offset += drillAnimation.getHeight();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(180);
                g.drawAnimation((Animation)drill.get(i), x, y + offset);
                offset -= drillAnimation.getHeight();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(270);
                g.drawAnimation((Animation)drill.get(i), x + offset, y);
                offset += drillAnimation.getWidth();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(90);
                g.drawAnimation((Animation)drill.get(i), x + offset, y);
                offset -= drillAnimation.getWidth();
            }
            else
            {
                System.out.println("It's not right! It's wrong!");
            }
        }
    }

    public void drillDown(){
        if (drill.size() < 12) {
            drill.add(drillAnimation);
        }
    }

    public void drillUp() {
        if (drill.size() > 2) {
            drill.remove(drill.size() - 1);
        }
    }

    public void drillLeft(){
        if(drill.size() > 2) {
            return;
        }

        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            x -= drillAnimation.getWidth();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            x += drillAnimation.getWidth();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            y -= drillAnimation.getHeight();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            y += drillAnimation.getHeight();
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
    }

    public void drillRight(){
        if(drill.size() > 2) {
            return;
        }

        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            x += drillAnimation.getWidth();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            x -= drillAnimation.getWidth();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            y += drillAnimation.getHeight();
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            y -= drillAnimation.getHeight();
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
    }
}

