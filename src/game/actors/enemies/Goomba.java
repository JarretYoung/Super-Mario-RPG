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
 */
public class Goomba extends Enemy implements Resettable, Buffable {

	private int damage;
	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		damage = 10;

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

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void addDamage(int addedDamage) {
		damage += addedDamage;
	}

	@Override
	public void makeBuffable() {
		this.addCapability(Status.BUFFABLE);
	}
}