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

    public Drill(int direction){

        //Initialize drill
        try {
            drillTipSprite = new SpriteSheet("drill_part.png", 32, 32);
            drillSprite = new SpriteSheet("drill_tip.png", 32, 32);

            drillTipAnimation = new Animation(drillTipSprite, 10);
            drillAnimation = new Animation(drillSprite, 10);

            drill = new ArrayList<Animation>();
            drill.add(drillTipAnimation);
            drill.add(drillAnimation);

            x = 50;
            y = 100;
            this.direction = direction;

        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public void drawDrill(Graphics g){
        int offset = 0;
        for(int i = 0; i < drill.size(); i++){
            if(direction == GameStatics.PLAYER_ORIENTATION_N) {
                g.drawAnimation((Animation)drill.get(i), x, y + offset);
                offset += 32;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
                g.drawAnimation((Animation)drill.get(i), x, y + offset);
                offset -= 32;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
                g.drawAnimation((Animation)drill.get(i), x + offset, y);
                offset += 32;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
                g.drawAnimation((Animation)drill.get(i), x + offset, y);
                offset -= 32;
            }
            else
            {
                System.out.println("It's not right! It's wrong!");
            }
        }
    }

    public void drillDown(){
        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            y += 32;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            y -= 32 ;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            x += 32;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            x-= 32;
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
        drill.add(drillAnimation);
    }

    public void drillUp(){
        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            y -= 32;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            y += 32 ;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            x -= 32;
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            x += 32;
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
        drill.remove(drill.size() -1);
    }

    public void drillLeft(){

    }

    public void drillRight(){

    }
}

