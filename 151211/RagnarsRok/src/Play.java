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
        drill = new Drill(GameStatics.PLAYER_ORIENTATION_N);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        if(sbg.getCurrentState().getID() == 1){
            world.drawWorld(220, 220, g);
            world.animateWorld(220, 220, g);
            drill.drawDrill(g);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
    }

    public int getID() {
        return 1;
    }
}
