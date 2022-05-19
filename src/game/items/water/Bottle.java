package game.items.water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actors.Buffable;

import java.util.Stack;

public class Bottle extends Item implements WaterContainer {

    /***
     * Constructor.
     *
     */
    private Stack<MagicalWater> magicalWaterLevel;
    public Bottle() {
        super("Bottle", 'b', false);
        magicalWaterLevel = new Stack<>();
        this.addCapability(Status.HAS_BOTTLE);
         //addAction();
    }

    @Override
    public void refill(MagicalWater magicalWater) {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }



    @Override
    public String peek() {
        return magicalWaterLevel.peek().toString();
    }

    @Override
    public Stack<MagicalWater> getMagicalWaterLevel() {
        return null;
    }

    @Override
    public void addMagicalWater(MagicalWater water) {

    }

}
