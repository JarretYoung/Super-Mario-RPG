package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actors.Buffable;

import java.util.Stack;

public class Bottle extends Item implements WaterContainer{
    private final int MAX_CAPACITY;
    private Stack<MagicalWater> magicalWater;
    /***
     * Constructor.
     *
     */
    public Bottle() {
        super("Bottle", 'b', false);
        addCapability(Status.HAS_BOTTLE);
        magicalWater = new Stack<>();
        MAX_CAPACITY = 10;
    }

    public String filled(Buffable actor, MagicalWater water){
        String ret = "";
        if(!isFull()){
            refill(water);
            ret = actor + " has filled up their bottle with " + water + " (" + magicalWater.size() + "/" + MAX_CAPACITY + ")";
        }
        else{
            ret = "Bottle is full!";
        }
        return ret;
    }

    public boolean isFull(){
        boolean flag = false;
        if(magicalWater.size() ==  MAX_CAPACITY){
            flag = true;
        }
        return flag;
    }

    private void refill(MagicalWater water) {
        for(int i = magicalWater.size() - 1; i < MAX_CAPACITY; i++)
            magicalWater.push(water);
    }
}
