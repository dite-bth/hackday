/**
 * Created by Linus on 2015-12-03.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.*;

import java.io.InputStream;

public class Play extends BasicGameState {

    private World world;
    private Drill drill;
    private City city1, city2, city3, city4;

    public Play() {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        world = new World();
        drill = new Drill(GameStatics.PLAYER_ORIENTATION_N, gc.getWidth()/2-32, 214);
        city1 = new City(GameStatics.PLAYER_ORIENTATION_N);
        city2 = new City(GameStatics.PLAYER_ORIENTATION_S);
        city3 = new City(GameStatics.PLAYER_ORIENTATION_E);
        city4 = new City(GameStatics.PLAYER_ORIENTATION_W);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        if(sbg.getCurrentState().getID() == 1){
            world.animateWorld(gc.getWidth()/2, gc.getHeight()/2, g);
            drill.drawDrill(g);
            city1.animateCity(gc, g, gc.getTime());
            city2.animateCity(gc, g, gc.getTime());
            city3.animateCity(gc, g, gc.getTime());
            city4.animateCity(gc, g, gc.getTime());
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        if(gc.getInput().isKeyPressed(Input.KEY_S)){
            if(world.getResourceAt(drill.getDrillPositionX(), drill.getDrillPositionY()) == -1) {
                drill.drillDown();
            }
            else if(drill.canDrillDown()){
                int harvest = world.harvestResourceAt(drill.getDrillPositionX(), drill.getDrillPositionY());
                drill.drillDown();
            }
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
        else if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
            city1.activateCityShield(gc.getTime());
        }
        SoundStore.get().poll(0);
    }

    public int getID() {
        return 1;
    }
}
