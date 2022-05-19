package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
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
 * @version 2.0 26/4/2022
 *
 */
abstract public class Koopa extends Enemy implements Resettable {

    /**
     * Health (while active)
     */
    private int hitPoints_active;
    /**
     * Constructor.
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.hitPoints_active = 100; //This the Koopa's hp when it is in an active state
        this.addCapability(Status.ACTIVE);
        this.addItemToInventory(new SuperMushroom());

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

    public void setHitPoints_active(int hitPoints_active) {
        this.hitPoints_active = hitPoints_active;
    }

    /** This method is used to assign a new intrinsic weapon to the Koopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
