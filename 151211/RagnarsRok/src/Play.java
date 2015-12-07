/**
 * Created by Linus on 2015-12-03.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.io.InputStream;

public class Play extends BasicGameState {

    private World world;
    private Drill drill;

    public Play() {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        world = new World();
        drill = new Drill(GameStatics.PLAYER_ORIENTATION_W);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        if(sbg.getCurrentState().getID() == 1){
            world.drawWorld(gc.getWidth()/2, gc.getHeight()/2, g);
            world.animateWorld(gc.getWidth()/2, gc.getHeight()/2, g);
            drill.drawDrill(g);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        if(gc.getInput().isKeyPressed(Input.KEY_S)){
            drill.drillDown();
        }
        else if(gc.getInput().isKeyPressed(Input.KEY_W)){
            drill.drillUp();
        }
        else if(gc.getInput().isKeyPressed(Input.KEY_D)){
            drill.drillRight();
        }
        else if(gc.getInput().isKeyPressed(Input.KEY_A)){
            drill.drillLeft();
        }
    }

    public int getID() {
        return 1;
    }
}
