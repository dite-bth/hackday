/**
 * Created by Linus on 2015-12-02.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

public class World {

    /** Game statics **/
    private GameStatics gameStatics;

    /** Sprites **/
    private SpriteSheet citySprite;
    private SpriteSheet planetSprite;
    private SpriteSheet ironSprite;
    private SpriteSheet ammoSprite;
    private SpriteSheet shieldSprite;

    /** Animations **/
    private Animation cityAnimation;
    private Animation planetAnimation;
    private Animation ironAnimation;
    private Animation ammoAnimation;
    private Animation shieldAnimation;

    private int[][] resources;

    private Random rand;

    public World(){
        //Randomize resources
        resources = new int[20][20];
        rand = new Random();
        for(int i=0; i<resources.length; i++){
            for(int j=0; j<resources[i].length; j++) {
                if (i <= 3 && j <= 3) {
                    resources[i][j] = -1;
                }
                else if (i <= 3 && j >= 16 ){
                    resources[i][j] = -1;
                }
                else if (i >= 16 && j <= 3){
                    resources[i][j] = -1;
                }
                else if (i >= 16 && j >= 16) {
                    resources[i][j] = -1;
                }
                else {
                    resources[i][j] = rand.nextInt((3 - 1) + 1) + 1;
                }
            }
        }

        //Initialize animations
        try {
            citySprite = new SpriteSheet("city.png", 128, 256);
            planetSprite = new SpriteSheet("planet.png", 832, 832);
            ironSprite = new SpriteSheet("iron.png", 32, 32);
            ammoSprite = new SpriteSheet("ammo.png", 32, 32);
            shieldSprite = new SpriteSheet("shield.png", 32, 32);

            cityAnimation = new Animation(citySprite, 1);
            planetAnimation = new Animation(planetSprite,1);
            ironAnimation = new Animation(ironSprite, 100);
            ironAnimation.setDuration(0,2000);
            ammoAnimation = new Animation(ammoSprite, 100);
            ammoAnimation.setDuration(0,3000);
            shieldAnimation = new Animation(shieldSprite, 100);
            shieldAnimation.setDuration(0,4000);
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public void drawWorld(int startX, int startY, Graphics g) {
        int offset = 96;
        for(int i=0; i<resources.length; i++) {
            for (int j = 0; j <resources[i].length; j++) {
                if (getAnimationAt(i, j) != null) {
                    g.drawImage(getAnimationAt(i, j).getImage(0), (startX + offset - planetAnimation.getWidth()/2 +
                            getAnimationAt(i,j).getWidth() * i), (startY + offset - planetAnimation.getHeight()/2 +
                            getAnimationAt(i,j).getHeight() * j));
                }
            }
        }
    }

    public void animateWorld(int startX, int startY, Graphics g){
        int offset = 96;
        //Draw planet
        g.drawAnimation(planetAnimation, startX - planetAnimation.getWidth()/2, startY - planetAnimation.getHeight()/2);
        //Draw resources
        for(int i=0; i<resources.length; i++) {
            for (int j = 0; j <resources[i].length; j++) {
                if (getAnimationAt(i, j) != null) {
                  g.drawAnimation(getAnimationAt(i, j), (startX + offset - planetAnimation.getWidth()/2 +
                          getAnimationAt(i,j).getWidth() * i), (startY + offset  - planetAnimation.getHeight()/2 +
                          getAnimationAt(i,j).getHeight() * j));
                }
            }
        }
    }

    public int getResourceAt (int x, int y){
        return resources[x][y];
    }

    public SpriteSheet getSpriteAt (int x, int y) {
        switch (getResourceAt(x, y)) {
            case GameStatics.RESOURCE_SHIELD:
                return shieldSprite;
            case GameStatics.RESOURCE_AMMO:
                return ammoSprite;
            case GameStatics.RESOURCE_REPAIR:
                return ironSprite;
            default:
                return null;
        }
    }

    public Animation getAnimationAt (int x, int y){
        switch(getResourceAt(x, y)){
            case GameStatics.RESOURCE_SHIELD:
                return shieldAnimation;
            case GameStatics.RESOURCE_AMMO:
                return ammoAnimation;
            case GameStatics.RESOURCE_REPAIR:
                return ironAnimation;
            default:
                return null;
        }
    }
}
