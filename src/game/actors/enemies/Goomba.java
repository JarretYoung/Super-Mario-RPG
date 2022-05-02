package game.actors.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.Enemy;
import game.reset.Resettable;
import game.behaviours.*;

/**
 * A little fungus guy.
 *
 * @editor Garret Yong Shern Min
 */
public class Goomba extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviour().put(7, new SuicideBehaviour(this, 10));
	}

	/** This method is used to assign a new intrinsic weapon to the Goomba
	 *
	 * @return a new instance of intrinsic weapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kick");
	}
}