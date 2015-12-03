/**
 * Created by Linus on 2015-12-02.
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

public class RagnarsRok extends BasicGame
{
    public RagnarsRok(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        //Get input - TODO: 2015-12-02
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("SPACE!", 40, 140);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new RagnarsRok("Ragnar(s)rök - or the end of the World"));
            appgc.setDisplayMode(1000, 1000, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(RagnarsRok.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}