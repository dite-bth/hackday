/*
  __ ___ _ __ _  _ __ __ _| |_   _| |_ __ __ _  _ __ _ ___ __
   / _/ _ \ '_ \ || / _/ _` |  _| |_  | '_ \_ \ || / _` / _ \_ \
   \__\___/ .__/\_, \__\__,_|\__| |__/|_,__/__/ ,_/\__. \___/__/
          |_|   |__/                           \__|   |_|
             _                 _, ,_                 _
             \\`'. .-'``'-. .'`// \\`'. .-'``'-. .'`//
              ;   `        `   ;   ;   '        '   ;
              /                |   |                \
            /;,      _     _    \ /    _     _      ,;\
           |;;'     (()__ (()    |    ()) __())     ';;|
           \  -.__     \_/ __.-  \  -.__ \_/     __.-  /
            `, .-'/ ;'  7 \'-. ,' `, .-'/ 7  '; \'-. .'
              `-,'         `,-'     `-.'         `.-'
                  |`''--''`|           |`' --''`|
                ,'       ';;          ,;;'       '.
             , '           ;;       ,;'            `.
           ,;,               \     /                ,;,
         ,;;;'`              ,;   /                `';;;,
       ,'                  ,;;;\;';;.                    \
      /            \   |      ';|;'      |   /            ;
     /             |   |  |     |     |  |   |             \
    .;;            |   |  |     |     |  |   |            ;;.
   ,;'             |;, |  |     ;     |  | ,;|             ';,
  /|               |;' |  |     |     |  | ';|               |\
 '  '.             |   |  |     ;\    |  |   |             .'  '
 | ,;;`-._        .'   |   '.   / '-.'   |   `.        _.-';;, |
  \;;'    `'-.--'(_,   ,) , ,)-`   (, , (,   ,_)`--.-'`    `';/
   `-._       _,-' '-`-'`-'-'       '-`-'`-'-' `-,_       mx-'
 */
package src;

/**
 *
 * @author eyerash-zen
 */
public class HumanPlayer {
    private HumanInput input; //Input object for this user.
    private int humanHealthPoint; //Current Healtpoint.
    private int humanScore = 0; //Current score. 
    private int humanAmmo = 0; //Current ammo.
    private double humanEnergy; //Curent energy.
    
    public HumanPlayer(HumanInput input, int humanHealthPoint, int humanEnergy, int humanScore, int humanAmmo){ //Needs controller object.
        this.input = input; 
        this.humanHealthPoint = humanHealthPoint;
        this.humanEnergy = humanEnergy;
    }
    
    public int getHumanHealthPoint(){
        return this.humanHealthPoint;
    }
    public void setHumanHealthPoint(int healthPoint){
        this.humanHealthPoint = healthPoint;
    }
    public int getHumanScore(){
        return this.humanScore;
    }
    public void setHumanScore(int humanScore){
        this.humanScore = humanScore;
    }
    public int getHumanAmmo(){
        return this.humanAmmo;
    }
    
    
}
