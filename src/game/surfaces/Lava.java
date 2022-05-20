package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {
    private int damage = 15;

    public Lava() {
        super('L');
    }

    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(this.damage);
        }

    }
}