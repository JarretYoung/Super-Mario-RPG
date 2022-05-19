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

public class Fountain extends Ground implements WaterStorage {

    private final int MAX_CAPACITY;

    private Stack<MagicalWater> magicalWaterStack;

    private int counter;

    private String name;

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar, String name) {
        super(displayChar);
        setName(name);
        MAX_CAPACITY = 10;
        this.refill(new MagicalWater(this));
        counter = 0;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(actor.hasCapability(Status.BUFFABLE))
            actions.add(new DrinkAction(this));

        if(actor.hasCapability(Status.HAS_BOTTLE))
            actions.add(new FillUpAction(this));

        return actions;
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
            ret = by + " drinks from " + this + getCapacity() + magicalWaterStack.pop().drinked(by);
        }
        else
            ret = "Fountain's empty!";
        return ret;
    }

    public String FilledUpFrom(Buffable by, WaterStorage container){
        String ret = "";

        if(!isEmpty()){
            MagicalWater water = magicalWaterStack.pop();
            container.filled(by, water);
            ret = by + " fills up from " + this + getCapacity() + water.drinked(by);
        }
        else
            ret = "Fountain's empty!";
        return ret;
    }

    private boolean isEmpty() {
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
        for(int i = magicalWaterStack.size() - 1; i < MAX_CAPACITY; i++)
            magicalWaterStack.push(water);
    }

    public String getCapacity(){
        String ret = "(" + magicalWaterStack.size() + "/" + MAX_CAPACITY + ")";
        return ret;
    }

    public String peek(){
        String ret = "";
        if(!isEmpty())
            ret = this.magicalWaterStack.peek() + "";
        else
            ret = "nothing";
        return ret;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if(isEmpty())
            counter += 1;
        else if(!isEmpty())
            counter = 0;
        if(counter == 5)
            refill(new MagicalWater(this));
    }
}
