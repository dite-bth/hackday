/**
 * Created by Linus on 2015-12-02.
 */
import java.util.Random;

public class World {

    static int RESOURCE_MISSILE = 1;
    static int RESOURCE_SHIELD = 2;
    static int RESOURCE_REPAIR = 3;
    static int RESOURCE_EMPTY = 4;

    private int[] resources;
    public World(){
        resources = new int[576];
        for(int i; i == resources.length -1; i++){
            resources[i] = new Random().nextInt((1 - 4) + 1) + 1;
        }
    }

    public int getResource (int x, int y){
        int returnvalue = resources[x+x*y];
        resources[x+x*y] = 0;
        return returnvalue;
    }
}
