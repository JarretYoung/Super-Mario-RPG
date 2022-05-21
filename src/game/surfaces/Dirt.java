package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Constructor
	 */
	public Dirt() {
		super('.');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Status.SWIMMABLE_ENEMY))
			return false;
		return true;
	}
}
