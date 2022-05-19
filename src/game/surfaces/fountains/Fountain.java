package game.surfaces.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DrinkAction;
import game.actions.FillUpAction;
import game.actions.JumpAction;
import game.actors.Buffable;
import game.items.water.MagicalWater;
import game.items.water.WaterContainer;

import java.util.Stack;

public class Fountain extends Ground implements WaterContainer {
    private int counter;
    private final int MAX_CAPACITY = 10;
    private Stack<MagicalWater> magicalWaterLevel;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        magicalWaterLevel = new Stack<>();
    }

    @Override
    public void refill(MagicalWater magicalWater) {

        for(int i = 0; i < getCapacity(); i++){
            this.addMagicalWater(magicalWater);
        }
    }

    @Override
    public int getCapacity() {
        return this.MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return magicalWaterLevel.isEmpty();
    }

    @Override
    public String peek() {
        return magicalWaterLevel.peek().toString();
    }

    @Override
    public Stack<MagicalWater> getMagicalWaterLevel() {
        return this.magicalWaterLevel;
    }

    @Override
    public void addMagicalWater(MagicalWater water) {
        this.getMagicalWaterLevel().add(water);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(actor instanceof Buffable && this.getMagicalWaterLevel().size() > 0)
            actions.add(new DrinkAction(this.getMagicalWaterLevel().peek()));

        if(actor.hasCapability(Status.HAS_BOTTLE))
            actions.add(new FillUpAction());
        return actions;
    }

    public void tick(Location location, MagicalWater magicalWater) {
        super.tick(location);
        if(isEmpty()){
            counter += 1;
            if (counter == 5){
                refill(magicalWater);
            }
        }
    }
}
