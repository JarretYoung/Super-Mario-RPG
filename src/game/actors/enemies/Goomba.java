package game.actors.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actors.Buffable;
import game.actors.enemies.Enemy;
import game.reset.Resettable;
import game.behaviours.*;

/**
 * A little fungus guy.
 *
 * @author  Garret Yong Shern Min
 * @version 1.0 18/5/2022
 */
public class Goomba extends Enemy implements Resettable, Buffable {

	/**
	 * Damage points
	 */
	private int damage;
	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		damage = 10;

		// Make this enemy buffable
		this.makeBuffable();

		// Adding standard behaviours to the enemy
		this.getBehaviour().put(10, new WanderBehaviour());
		this.getBehaviour().put(9, new AttackBehaviour());
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

	/** This is an accessor to get the damage points of this Enemy
	 *
	 * @return an integer value of the damage points of this Enemy
	 */
	@Override
	public int getDamage() {
		return damage;
	}

	/** This is a setter to add upon the current damage points of this Enemy
	 *
	 * @param addedDamage as integer value of how many damage points to add upon the current
	 */
	@Override
	public void addDamage(int addedDamage) {
		damage += addedDamage;
	}

	/**
	 * This method is used to add the capability: Buffable to the Enemy
	 */
	@Override
	public void makeBuffable() {
		this.addCapability(Status.BUFFABLE);
	}
}