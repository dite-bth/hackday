/**
 * Created by Linus on 2015-12-07.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Drill {

    /** Sprites **/
    private SpriteSheet drillTipSprite;
    private SpriteSheet drillSprite;
    private SpriteSheet drillBaseSprite;

    /** Animations **/
    private Animation drillTipAnimation;
    private Animation drillAnimation;
    private Animation drillBaseAnimation;

    /** Sound **/
    private Audio wawWallHit;
    private Audio wawDrillDown;

    private ArrayList drill;

    private int x;
    private int y;
    private int position[];
    private int direction;


    public Drill(int drillDirection){

        //Initialize drill
        switch(drillDirection){
            case GameStatics.PLAYER_ORIENTATION_N:
                position = new int[]{9,0};
                break;
            case GameStatics.PLAYER_ORIENTATION_S:
                position = new int[]{8,19};
                break;
            case GameStatics.PLAYER_ORIENTATION_E:
                position = new int[]{19,9};
                break;
            case GameStatics.PLAYER_ORIENTATION_W:
                position = new int[]{0,8};
                break;
        }

        try {
            drillTipSprite = new SpriteSheet("/img/drill_tip.png", 32, 32);
            drillSprite = new SpriteSheet("/img/drill_part.png", 32, 32);
            drillBaseSprite = new SpriteSheet("/img/drill_base.png", 32, 32);

            direction = drillDirection;

            drillTipAnimation = new Animation(drillTipSprite, 10);
            drillAnimation = new Animation(drillSprite, 10);
            drillBaseAnimation = new Animation(drillBaseSprite,50);
            drillBaseAnimation.setLooping(false);

            x = position[0]*drillAnimation.getWidth();
            y = position[1]*drillAnimation.getHeight();

            drill = new ArrayList<Animation>();
            drill.add(drillTipAnimation);
            drill.add(drillBaseAnimation);

            wawWallHit = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/sfx/drill_wall_hit.wav"));
            wawDrillDown = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/sfx/drilldown.wav"));



        }catch(SlickException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawDrill(Graphics g, int x, int y){
        int offsetX = x - 10*drillAnimation.getWidth();
        int offsetY = y - 10*drillAnimation.getHeight();
        for(int i = drill.size()-1; i >=0 ; i--){
            if(direction == GameStatics.PLAYER_ORIENTATION_N) {
                g.drawAnimation((Animation)drill.get(i), offsetX + position[0]*drillAnimation.getWidth(),
                        offsetY - 2*drillAnimation.getHeight());
                offsetY += drillAnimation.getHeight();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(180);
                g.drawAnimation((Animation)drill.get(i), offsetX + position[0]*drillAnimation.getWidth(),
                        offsetY + 21*drillAnimation.getHeight());
                offsetY -= drillAnimation.getHeight();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(90);
                g.drawAnimation((Animation)drill.get(i), offsetX + 21*drillAnimation.getWidth(),
                        offsetY + position[1]*drillAnimation.getHeight());
                offsetX -= drillAnimation.getWidth();
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
                ((Animation)drill.get(i)).getCurrentFrame().setRotation(270);
                g.drawAnimation((Animation)drill.get(i), offsetX - 2*drillAnimation.getWidth(),
                        offsetY + position[1]*drillAnimation.getHeight());
                offsetX += drillAnimation.getWidth();
            }
            else
            {
                System.out.println("It's not right! It's wrong!");
            }
        }
    }

    public int getDrillPositionX(){
        return position[0];
    }

    public int getDrillPositionY(){
        return position[1];
    }

    public boolean canDrillDown(){
        if(drill.size() < 11){
            return true;
        }
        else{
            return false;
        }
    }

    public void drillDown(){
        if (drill.size() < 12) {
            wawDrillDown.playAsSoundEffect(1f, 1f, false);
            drill.add(1, drillAnimation);
            if(direction == GameStatics.PLAYER_ORIENTATION_N) {
                position[1] ++;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
                position[1] --;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
                position[0] --;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
                position[0] ++;
            }
            else
            {
                System.out.println("It's not right! It's wrong!");
            }
        }
    }

    public void drillUp() {
        if (drill.size() > 2) {
            drill.remove(drill.size() - 2);
            if(direction == GameStatics.PLAYER_ORIENTATION_N) {
                position[1] --;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
                position[1] ++;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
                position[0] ++;
            }
            else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
                position[0] --;
            }
            else
            {
                System.out.println("It's not right! It's wrong!");
            }
        }
    }

    public void drillLeft(){
        if(drill.size() > 2) {
            wawWallHit.playAsSoundEffect(1f, 1f, false);
            return;
        }

        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            x -= drillAnimation.getWidth();
            if(position[0] > 4) {
                position[0]--;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            x += drillAnimation.getWidth();
            if(position[0] < 15) {
                position[0]++;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            y -= drillAnimation.getHeight();
            if(position[1] < 15) {
                position[1]++;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            y += drillAnimation.getHeight();
            if(position[1] > 4) {
                position[1]--;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
    }

    public void drillRight(){
        if(drill.size() > 2) {
            wawWallHit.playAsSoundEffect(1f, 1f, false);
            return;
        }

        if(direction == GameStatics.PLAYER_ORIENTATION_N) {
            x += drillAnimation.getWidth();
            if(position[0] < 15) {
                position[0]++;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_S) {
            x -= drillAnimation.getWidth();
            if(position[0] > 4) {
                position[0]--;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_E) {
            y += drillAnimation.getHeight();
            if(position[1] > 4) {
                position[1]--;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else if(direction == GameStatics.PLAYER_ORIENTATION_W) {
            y -= drillAnimation.getHeight();
            if(position[1] < 15) {
                position[1]++;
            }
            else{
                wawWallHit.playAsSoundEffect(1f, 1f, false);
            }
        }
        else
        {
            System.out.println("It's not right! It's wrong!");
        }
    }
}

