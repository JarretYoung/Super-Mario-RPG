package game.surfaces.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.water.HealthWater;

public class HealthFountain extends Fountain{
    /**
     * Constructor.
     *
     */
    public HealthFountain() {
        super('H');
        this.refill(new HealthWater());
    }

    @Override
    public void tick(Location location) {
        super.tick(location, new HealthWater());
    }


}
