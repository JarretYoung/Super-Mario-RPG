package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DrinkAction;
import game.actors.Buffable;

import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements WaterStorage{
    private final int MAX_CAPACITY;
    private Stack<MagicalWater> magicalWaterStack;
    /***
     * Constructor.
     *
     */
    public Bottle() {
        super("Bottle", 'b', false);
        addCapability(Status.HAS_BOTTLE);
        magicalWaterStack = new Stack<>();
        MAX_CAPACITY = 10;
    }

    public String filled(Buffable actor, MagicalWater water){
        String ret = "";
        if(!isFull()){
            refill(water);
            ret = actor + " has filled up their bottle with " + water + " (" + magicalWaterStack.size() + "/" + MAX_CAPACITY + ")";
        }
        else{
            ret = "Bottle is full!";
        }
        return ret;
    }

    public String DrinkedFrom(Buffable by){
        String ret = "";

        if(!isEmpty()){
            MagicalWater water = magicalWaterStack.pop();
            ret = by + " drinks from " + this + getCapacity() + water.drinked(by);
        }
        else
            ret = "Bottle's empty!";
        return ret;
    }

    public boolean isEmpty() {
        boolean flag = false;
        flag = magicalWaterStack.isEmpty();
        return flag;
    }

    public boolean isFull(){
        boolean flag = false;
        if(magicalWaterStack.size() ==  MAX_CAPACITY){
            flag = true;
        }
        return flag;
    }

    private void refill(MagicalWater water) {
        for(int i = magicalWaterStack.size(); i < MAX_CAPACITY; i++)
            magicalWaterStack.push(water);
    }

    public String getCapacity(){
        String ret = "(" + magicalWaterStack.size() + "/" + MAX_CAPACITY + ")";
        return ret;
    }

    public String getStack(){
        return this.magicalWaterStack.toString();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(isFull())
            this.addCapability(Status.HAS_FULL_BOTTLE);
        super.tick(currentLocation, actor);
    }

}
