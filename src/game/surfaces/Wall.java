package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.surfaces.HighGround;

/**
 * Wall, a high ground
 */
public class Wall extends HighGround {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#', "Wall",80, 20);
	}

	/**
	 * can block thrown objects
	 * @return true/false
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
