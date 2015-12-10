/**
 * Created by Linus on 2015-12-02.
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class RagnarsRok extends StateBasedGame{

    public static final String gamename = "Ragnar(s)rök - or the end of the world";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int xSize = 1200;
    public static final int ySize = 1200;

    public RagnarsRok(String gamename){
        super(gamename);
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        this.addState(new Menu());
        this.addState(new Play());
        //this.getState(play).init(gc, this);
        //this.getState(menu).init(gc, this);

        //Start with the Menu state
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new RagnarsRok(gamename));
            appgc.setDisplayMode(xSize, ySize, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}