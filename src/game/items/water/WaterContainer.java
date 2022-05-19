package game.items.water;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;

import java.util.Stack;

public interface WaterContainer {
    public void refill(MagicalWater magicalWater);
    public int getCapacity();
    public boolean isEmpty();
    public String peek();
    public Stack<MagicalWater> getMagicalWaterLevel();
    public void addMagicalWater(MagicalWater water);
}
