package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;

public interface WaterStorage {

    public String DrinkedFrom(Buffable by);

    public String filled(Buffable by, MagicalWater water);

    public boolean isEmpty();

    public String getStack();
}
