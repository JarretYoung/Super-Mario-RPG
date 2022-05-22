package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DrinkAction;
import game.actions.FillUpAction;
import game.actors.Buffable;
import game.items.MagicalWater;
import game.items.WaterStorage;

import java.util.Stack;
/**
 * Class for fountain
* @author Jastej Gill
 * @version 2.0 21/5/2022
 */
public abstract class Fountain extends Ground implements WaterStorage {

    /**
     * Maximum capacity of fountain
     */
    private final int MAX_CAPACITY;

    /**
     * Stack of magical water
     */
    private Stack<MagicalWater> magicalWaterStack;

    /**
     * Counter for tick method
     */
    private int counter;

    /**
     * Name of fountain
     */
    private String name;

    /**
     * Amount of water drinked
     */
    private final int DRINK_AMOUNT;

    /**
     * Sets name of fountain
     * @param name name of fountain
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name of fountain
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor.
     * @param  name of Fountain
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar, String name) {
        super(displayChar);
        this.addCapability(Status.FOUNTAIN);
        setName(name);
        MAX_CAPACITY = 10;
        DRINK_AMOUNT = 5;
        magicalWaterStack = new Stack<>();
        this.refill(new MagicalWater(this));
        counter = 0;
    }

    /**
     * Gets action actor can perform on Fountain
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(location.containsAnActor()){
            if(actor.hasCapability(Status.BUFFABLE) && !actor.hasCapability(Status.PLAYER))
                actions.add(new DrinkAction(this, actor));

            if(actor.hasCapability(Status.HAS_BOTTLE) && !actor.hasCapability(Status.HAS_FULL_BOTTLE))
                actions.add(new FillUpAction(this));

        }
        return actions;
    }

    /**
     * Called when Player performs FillUpAction on WaterStorage
     * @param actor Actor filling up from from WaterStorage
     * @param water water by obtains
     * @return String of result
     */
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

    /**
     * Called when Player performs DrinkAction on WaterStorage
     * @param by Actor drinking from from from WaterStorage
     * @return String of result
     */
    public String DrinkedFrom(Buffable by){
        String ret = "";

        if(!isEmpty() && magicalWaterStack.size() >= DRINK_AMOUNT){
            MagicalWater water = magicalWaterStack.pop();
            for(int i = 0; i < 4; i++)
                magicalWaterStack.pop();
            ret = by + " drinks from " + this + water.drinked(by);
        }
        else if(magicalWaterStack.size() < DRINK_AMOUNT)
            ret = "Not enough water!";
        else
            ret = this + "'s empty!, come back later";
        return ret;
    }

    /**
     * Called when Player performs FillUpAction on WaterStorage
     * @param by Actor filling up from from WaterStorage
     * @param container water storage player fills from
     * @return String of result
     */
    public String FilledUpFrom(Buffable by, WaterStorage container){
        String ret = "";

        if(!isEmpty()){
            MagicalWater water = magicalWaterStack.pop();
            container.filled(by, water);
            ret = by + " fills up from " + this;
        }
        else
            ret = "Fountain's empty!";
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
     * Checks when water storage is full
     * @return boolean value, true if full
     */
    public boolean isFull(){
        boolean flag = false;
        if(magicalWaterStack.size() ==  MAX_CAPACITY){
            flag = true;
        }
        return flag;
    }

    /**
     * Refills Fountain
     * @param water water to refill
     */
    private void refill(MagicalWater water) {
            for(int i = 0; i < MAX_CAPACITY; i++)
                magicalWaterStack.push(water);
    }

    /**
     * Gets capacity of Fountain
     * @return String of fountain capacity
     */
    public String getCapacity(){
        String ret = "(" + magicalWaterStack.size() + "/" + MAX_CAPACITY + ")";
        return ret;
    }

    /**
     * Checks top element of stack
     * @return String of top element
     */
    public String peek(){
        String ret = "";
        if(!isEmpty())
            ret = this.magicalWaterStack.peek().toString() + "";
        else
            ret = "nothing";
        return ret;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(isEmpty())
            counter += 1;
        else if(!isEmpty())
            counter = 0;
        if(counter > 5)
            refill(new MagicalWater(this));
    }

    /**
     * Return String of Fountain object
     * @return String of Fountain object
     */
    public String toString(){
        return "Fountain " + getCapacity();
    }

    /**
     * Returns true if an Actor can enter this location.
     *
     * Prevent Swimmable enemies from entering
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.SWIMMABLE_ENEMY))
            return false;
        return true;
    }

    /**
     * Gets magicalWaterStack strin
     * @return string of magical water stack
     */
    public String getStack(){
        return this.magicalWaterStack.toString();
    }


}
