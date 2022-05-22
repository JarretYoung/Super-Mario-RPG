package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Lava Ground class
 * @author Lup Hoong
 * @version 1.0 18/5/2022
 */
public class Lava extends Ground {
    /**
     * Damage onto player when stepped onto
     */
    private int damage;

    /**
     * Constructor
     */
    public Lava() {
        super('L');
        this.damage = 15;
    }

    /**
     * Only allow player to enter
     * @param actor the Actor to check
     * @return true if actor is player
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Damage player each turn if player is on it
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(this.damage);
        }

    }
}