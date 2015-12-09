package src;

/**
 *
 * @author eyerash-zen
 */
public class HumanPlayer {
    private HumanInput input; //Input object for this user.
    private int humanHealthPoint; //Current Healtpoint.
    private int humanScore; //Current score. 
    private int humanAmmo; //Current ammo.
    private double humanEnergy; //Curent energy.
    
    public HumanPlayer(HumanInput input, int humanHealthPoint, int humanEnergy, int humanScore, int humanAmmo){ //Needs controller object.
        this.input = input; 
        this.humanHealthPoint = humanHealthPoint;
        this.humanEnergy = humanEnergy;
        this.humanAmmo = 0;
        this.humanScore = 0;
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
    public void setHumanAmmo(int setHumanAmmo){
        this.humanAmmo = setHumanAmmo;
    }
    public double humanEnergy(){
        return this.humanEnergy;
    }
    public void setHumanEnergy(double humanEnergy){
        this.humanEnergy = humanEnergy;
    }
    public HumanInput getHumanControllListener(){
        return this.input;
    } 
    
}
