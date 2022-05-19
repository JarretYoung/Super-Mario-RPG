package game.surfaces.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.water.PowerWater;

public class PowerFountain extends Fountain{
    /**
     * Constructor.
     *
     */
    public PowerFountain() {
        super('A');
        this.refill(new PowerWater());
    }


}
