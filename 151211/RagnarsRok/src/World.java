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
    private SpriteSheet planetSprite;


        /** Animations **/
    private Animation planetAnimation;

    private Resource[][] resources;

    private Random rand;

    //START Resource class
    private class Resource{
        private SpriteSheet ironSprite;
        private SpriteSheet ammoSprite;
        private SpriteSheet shieldSprite;
        private SpriteSheet stoneSprite;

        private Animation stoneAnimation;
        private Animation resourceAnimation;

        private int stoneType;
        private int resourceType;
        private boolean resourceVisible;

        public Resource(){
            try{
                initSpriteSheets();
            }catch (SlickException e){
                e.printStackTrace();
            }
            stoneType = rand.nextInt(2);
            final int randomType = rand.nextInt(100);
            if (randomType > 15) {
                resourceType = GameStatics.RESOURCE_AMMO;
            }
            else if (randomType > 30) {
                resourceType = GameStatics.RESOURCE_SHIELD;
            }
            else if (randomType > 55) {
                resourceType = GameStatics.RESOURCE_REPAIR;
            }
            else {
                resourceType = GameStatics.RESOURCE_STONE;
            }
            resourceVisible = false;
            try{
                initAnimation();
            }catch (SlickException e){
                e.printStackTrace();
            }
        }

        public Resource(int resourceType){
            try{
                initSpriteSheets();
            }catch (SlickException e){
                e.printStackTrace();
            }
            stoneType = rand.nextInt(2);
            this.resourceType = resourceType;
            resourceVisible = false;
            try{
                initAnimation();
            }catch (SlickException e){
                e.printStackTrace();
            }
        }

        private void initSpriteSheets() throws  SlickException{
            ironSprite = new SpriteSheet("/img/iron.png", 32, 32);
            ammoSprite = new SpriteSheet("/img/ammo.png", 32, 32);
            shieldSprite = new SpriteSheet("/img/shield.png", 32, 32);
            stoneSprite = new SpriteSheet("/img/stone.png", 32, 32);
        }

        private void initAnimation() throws SlickException{
            //Initialize animations
                if(resourceType == GameStatics.RESOURCE_AMMO) {
                    resourceAnimation = new Animation(ammoSprite, 100);
                    resourceAnimation.setDuration(0,6000);
                }
                else if(resourceType == GameStatics.RESOURCE_REPAIR) {
                    resourceAnimation = new Animation(ironSprite, 100);
                    resourceAnimation.setDuration(0,4000);
                }
                else if(resourceType == GameStatics.RESOURCE_SHIELD) {
                    resourceAnimation = new Animation(shieldSprite, 100);
                    resourceAnimation.setDuration(0,8000);
                }
                stoneAnimation = new Animation(stoneSprite,1);
                stoneAnimation.stop();
                stoneAnimation.setCurrentFrame(stoneType);
            }

        public int getResourceType(){
                return resourceType;
        }

        public int harvestResource(){
            int harvest = resourceType;
            resourceType = -1;
            return harvest;
        }

        public Animation getResourceSprite(){
            if(resourceVisible){
                return resourceAnimation;
            }
            else{
                return stoneAnimation;
            }
        }

        public Animation getAnimation(){
            if(resourceType == -1){
                return null;
            }
            if(resourceVisible){
                return resourceAnimation;
            }
            else{
                return stoneAnimation;
            }
        }

        public void setResourceVisible(){
            resourceVisible = true;
        }
    }//END Resource Class

    public World(){
        //Randomize resources
        resources = new Resource[20][20];
        rand = new Random();
        for(int i=0; i<resources.length; i++){
            for(int j=0; j<resources[i].length; j++) {
                if (i <= 3 && j <= 3) {
                    resources[i][j] = new Resource(-1);
                }
                else if (i <= 3 && j >= 16 ){
                    resources[i][j] = new Resource(-1);
                }
                else if (i >= 16 && j <= 3){
                    resources[i][j] = new Resource(-1);
                }
                else if (i >= 16 && j >= 16) {
                    resources[i][j] = new Resource(-1);
                }
                else {
                    resources[i][j] = new Resource();
                }
            }
        }

        //Initialize animations
        try {
            planetSprite = new SpriteSheet("/img/planet.png", 828, 828);
            planetAnimation = new Animation(planetSprite,1);
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
                    g.drawAnimation(getAnimationAt(i, j), (startX + offset - planetAnimation.getWidth() / 2 +
                            getAnimationAt(i, j).getWidth() * i),(startY + offset - planetAnimation.getHeight() / 2 +
                            getAnimationAt(i, j).getHeight() * j));
                }
            }
        }
    }

    public int getResourceAt (int x, int y){
        return resources[x][y].getResourceType();
    }

    public int harvestResourceAt (int x, int y){
        return resources[x][y].harvestResource();
    }

    public Animation getAnimationAt (int x, int y){
        return resources[x][y].getAnimation();
    }
}
