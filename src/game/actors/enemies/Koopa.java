package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Buffable;
import game.behaviours.AttackBehaviour;
import game.behaviours.DrinkBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;
import game.reset.Resettable;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.Status;

/**This class represents the Turtle enemy known as the Koopa which is a regular enemy in the Mario
 * universe. This class acts as an enemy to the player and will engage in combat with the player
 *
 * This enemy has 2 phases, active and dormant. When active, this enemy will wander around, follow the player
 * and attack the player. However, in dormant mode, no action can be performed by and on the Koopa.
 *
 * The only way to defeat a dormant Koopa is to possess a wrench item and conduct a BreakShellAction to fully
 * kill the Koopa and to make it drop a SuperMushroom
 *
 * @author Lup Hoong, Garret Yong Shern Min
 * @version 3.0 21/5/2022
 *
 */
abstract public class Koopa extends Enemy implements Resettable,Buffable {

    /**
     * Health (while active)
     */
    private int hitPoints_active;

    /**
     * Damage (while active)
     */
    private int damage;
    /**
     * Constructor.
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.ACTIVE);
        this.addItemToInventory(new SuperMushroom(false));

        damage = 30;
        this.makeBuffable();

        // Adding standard behaviours to the enemy
        this.getBehaviour().put(10, new WanderBehaviour());
        this.getBehaviour().put(9, new AttackBehaviour());
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if ((otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) && !this.hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this,direction));
        }
        if (this.hasCapability(Status.DORMANT)) {
            actions.add(new BreakShellAction(this,direction));
        }
        return actions;
    }


    /** This method it used to reduce the hitPoints_active (hitpoints of the active Koopa)
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        hitPoints_active -= points;
        if (hitPoints_active <= 0) {
            this.setDisplayChar('D');
            this.removeCapability(Status.ACTIVE);
            this.addCapability(Status.DORMANT);
            this.getBehaviour().clear();
        }
    }

    /** This method is used to get the active hit points
     *
     * @return
     */
    public int getHitPoints_active() {
        return hitPoints_active;
    }

    /** This method is used to set the active hit points
     *
     * @param hitPoints_active is the active hitpoints that the Koopa is to be instantiated with
     */
    public void setHitPoints_active(int hitPoints_active) {
        this.hitPoints_active = hitPoints_active;
    }

    /** This method is used to assign a new intrinsic weapon to the Koopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "punch");
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
