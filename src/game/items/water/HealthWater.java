package game.items.water;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;

public class HealthWater extends MagicalWater {
    /***
     * Constructor.
     */
    public HealthWater() {
        super("Health Water");
    }

    @Override
    public String drinked(Buffable by) {
        Actor actor = by;
        String result = actor + " has gained 50 hp";

        actor.heal(50);
        return result;
    }
}
