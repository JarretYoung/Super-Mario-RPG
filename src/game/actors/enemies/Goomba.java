package game.actors.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.Enemy;
import game.reset.Resettable;
import game.behaviours.*;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviour().put(7, new SuicideBehaviour(this));
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kick");
	}
}