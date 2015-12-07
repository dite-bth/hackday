/**
 * Created by Linus on 2015-12-03.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.IOException;

public class Menu extends BasicGameState {

    /** States **/
    public static final int menu = 0;
    public static final int play = 1;

    /** Sounds **/
    private Audio oggStream;

    /** Fonts for the menu **/
    private UnicodeFont headlineFont;
    private UnicodeFont menuFont;

    /** The menu **/
    private String[] menuItems = {"Start new game", "Exit game"};
    private int menuItemChoosen = 0;

    public Menu() {
        headlineFont = new UnicodeFont(new java.awt.Font("Showcard Gothic", Font.PLAIN, 40));
        headlineFont.getEffects().add(new ColorEffect(java.awt.Color.yellow));
        headlineFont.addAsciiGlyphs();

        menuFont = new UnicodeFont(new java.awt.Font("Showcard Gothic", Font.PLAIN, 30));
        menuFont.getEffects().add(new ColorEffect(java.awt.Color.yellow));
        menuFont.addAsciiGlyphs();
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        headlineFont.loadGlyphs();
        menuFont.loadGlyphs();
        try{
            oggStream = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("rodney_balai_-_funkamatic.ogg"));
            oggStream.playAsMusic(1.0f, 1.0f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        g.setColor(Color.yellow);
        //Draw headline
        g.setFont(headlineFont);
        int width = headlineFont.getWidth("Ragnar(s)rök");
        g.drawString("Ragnar(s)rök", gc.getWidth()/2-width/2, gc.getHeight()/20);

        //Draw menu
        g.setFont(menuFont);
        int y = gc.getHeight()/3;
        for (String item: menuItems) {
            width = menuFont.getWidth(item);
            g.drawString(item, gc.getWidth()/2-width/2, y);
            y += 65;
        }
        g.setColor(Color.orange);
        g.setLineWidth(3);
        g.drawOval(gc.getWidth()/2-menuFont.getWidth(menuItems[menuItemChoosen])/2 - 20, gc.getHeight()/3-10 +
                65*menuItemChoosen, menuFont.getWidth(menuItems[menuItemChoosen])+40, menuFont.getHeight(menuItems[menuItemChoosen])+24, 32);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        if(gc.getInput().isKeyPressed(Input.KEY_DOWN)){
            if(menuItemChoosen == menuItems.length -1){
                menuItemChoosen = 0;
            }
            else{
                menuItemChoosen ++;
            }
            gc.getInput().clearKeyPressedRecord();
        }
        if(gc.getInput().isKeyPressed(Input.KEY_UP)){
            if(menuItemChoosen == 0){
                menuItemChoosen = menuItems.length -1;
            }
            else{
                menuItemChoosen --;
            }
            gc.getInput().clearKeyPressedRecord();
        }
        if(menuItemChoosen == 1 && (gc.getInput().isKeyPressed(Input.KEY_SPACE) || gc.getInput().isKeyPressed(Input.KEY_ENTER))){
            gc.exit();
        }
        else if(menuItemChoosen == 0 && (gc.getInput().isKeyPressed(Input.KEY_SPACE) || gc.getInput().isKeyPressed(Input.KEY_ENTER))){
            sbg.enterState(play);
            oggStream.stop();
        }

        SoundStore.get().poll(0);
    }

    public int getID() {
        return 0;
    }
}
