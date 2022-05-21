package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Crippleable;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * The Special Effect left on the ground after attacking
	 */
	protected Item specialAttackEffect = null;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction is the direction that the target is at
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 * @param direction is the direction that the target is at
	 * @param specialAttackEffect is the specialEffect that can be dropped ont he ground during attacking
	 */
	public AttackAction(Actor target, String direction, Item specialAttackEffect) {
		this.target = target;
		this.direction = direction;
		this.specialAttackEffect = specialAttackEffect;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		String result;

		final int MAX_DAMAGE = 9999;

		if (!(rand.nextInt(100) <= weapon.chanceToHit()) && !actor.hasCapability(Status.INVINCIBLE)) {
			result = actor + " misses " + target + ".";
		}

		else {
			int damage = weapon.damage();
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

			// non-crippling attack
			if (target.hasCapability(Status.INVINCIBLE)) {
				target.hurt(0);
			} else if (actor.hasCapability(Status.INVINCIBLE)) {
				target.hurt(MAX_DAMAGE);
			} else {
				target.hurt(damage);

				// cripple weapon attack action
				if (actor.hasCapability(Status.CRIPPLE_ATTACK)) {
					Crippleable crippleWeapon = (Crippleable) weapon;
					if ((rand.nextInt(100)) <= crippleWeapon.getChanceToCripple()) {
						this.target.addCapability(Status.CRIPPLED);
					}
					result += System.lineSeparator() + target + " is crippled.";
				}
			}

			// attack and drop any special effects
			if (this.specialAttackEffect != null){
				map.locationOf(target).addItem(this.specialAttackEffect);
			}

			if (target.hasCapability(Status.SUPER)) {
				target.removeCapability(Status.SUPER);
				target.removeCapability(Status.TALL);
			}

			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
