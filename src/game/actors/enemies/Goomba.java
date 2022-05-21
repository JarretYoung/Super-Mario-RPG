package game.actors.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.Enemy;
import game.reset.Resettable;
import game.behaviours.*;

/**
 * A little fungus guy.
 *
 * @author  Garret Yong Shern Min
 */
public class Goomba extends Enemy implements Resettable {

	private int damage;
	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);

		// Adding standard behaviours to the enemy
		this.getBehaviour().put(10, new WanderBehaviour());
		this.getBehaviour().put(9, new AttackBehaviour());
		this.getBehaviour().put(8, new DrinkBehaviour());
		this.getBehaviour().put(6, new SuicideBehaviour(this, 10));
	}

	/** This method is used to assign a new intrinsic weapon to the Goomba
	 *
	 * @return a new instance of intrinsic weapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(damage, "kick");
	}
}