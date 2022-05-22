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

/**
 * Bottle class that extends abstract class Item
 * Capabilities: Is an item to store the waters of a fountain to carry around during the journey
 *
 * @author Jastej Gill
 * @version 2.0 21/5/2022
 */
public class Bottle extends Item implements WaterStorage{

    private Stack<MagicalWater> magicalWaterStack;
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.addCapability(Status.HAS_BOTTLE);
        magicalWaterStack = new Stack<>();
    }

    /**
     * Called when Player performs FillUpAction on WaterStorage
     * @param actor Actor filling up from from WaterStorage
     * @param water water by obtains
     * @return String of result
     */
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

    /**
     * Called when Player performs DrinkAction on WaterStorage
     * @param by Actor drinking from WaterStorage
     * @return String of result
     */
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

    /**
     * Checks when water storage is empty
     * @return boolean value, true if empty
     */
    public boolean isEmpty() {
        boolean flag = false;
        flag = magicalWaterStack.isEmpty();
        return flag;
    }

    /**
     * Gets stack of magical water
     * @return  magicalWaterStack
     */
    public String getStack(){
        return this.magicalWaterStack.toString();
    }


}
