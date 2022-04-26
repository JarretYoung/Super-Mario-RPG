package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.actions.ResetAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.Status;
import game.behaviours.SuicideBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

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
public class Koopa extends Actor implements Resettable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Health (while active)
     */
    private int hitPoints_active;
    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 20);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(9, new AttackBehaviour());
        this.behaviours.put(8, new SuicideBehaviour(this));

        this.hitPoints_active = 20; //This the Koopa's hp when it is in an active state, assume = 20
        this.addCapability(Status.KOOPA_ACTIVE);

        // Registering instance as a resettable object
        this.registerInstance();

        // Giving a SuperMushroom to drop upon death
        this.addItemToInventory(new SuperMushroom());
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
        if ((otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) && !this.hasCapability(Status.KOOPA_DORMANT)) {
            actions.add(new AttackAction(this,direction));
        }
        if (this.hasCapability(Status.KOOPA_DORMANT)) {
            actions.add(new BreakShellAction(this,direction));
        }

        //This is for testing purposes only
        actions.add(new ResetAction());

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
            this.removeCapability(Status.KOOPA_ACTIVE);
            this.addCapability(Status.KOOPA_DORMANT);
            this.behaviours.remove(10);
        }
    }

    /** This method is used to finish off the Koopa during its dormant phase
     *
     */
    public void destroyShell() {
        super.hurt(super.getMaxHp()); //if not then change to number
    }

    /**
     * Figure out what to do next.
     *
     * might want to consider removing actor here
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET_QUEUED)) {
            this.destroyShell();
            if (!this.isConscious()) {
                map.removeActor(this);

                // To be changed for string output is not appropriate
                return new DoNothingAction();
            }
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}