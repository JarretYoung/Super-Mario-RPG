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

    private Stack<MagicalWater> magicalWaterStack;
    /***
     * Constructor.
     *
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.addCapability(Status.HAS_BOTTLE);
        magicalWaterStack = new Stack<>();
    }

    public String filled(Buffable actor, MagicalWater water){
        String ret = "";
        if(magicalWaterStack.size() == 0){
           magicalWaterStack.push(water);
            ret = actor + " has filled up their bottle with " + water;
        }
        else if(magicalWaterStack.size() > 0){
            magicalWaterStack.push(water);
            ret = actor + " has filled up their bottle with " + water;
        }
        return ret;
    }

    public String DrinkedFrom(Buffable by){
        String ret = "";

        if(!isEmpty()){
            MagicalWater water = magicalWaterStack.pop();
            ret = by + " drinks from " + this + water.drinked(by);
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

    public String getStack(){
        return this.magicalWaterStack.toString();
    }


}
