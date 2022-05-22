package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.items.Fire;

/**
 * This class represents the behaviour to attack others around in the surrounding
 *
 * @author Garret Yong Shern Min
 * @version 2.0 20/5/2022
 */
public class AttackBehaviour extends Action implements Behaviour{

    /**
     * The Special Effect left on the ground after attacking
     */
    protected Item specialAttackEffect = null;

    /**
     * Basic constructor for when the enemy's attack does not have a Special Effect
     */
    public AttackBehaviour() {
    }

    /** Constructor for when an Actor is required to drop a Special Effect on the ground
     *
     * @param specialAttackEffect is the specialEffect that can be dropped ont he ground during attacking
     */
    public AttackBehaviour(Item specialAttackEffect) {
        this.specialAttackEffect = specialAttackEffect;
    }

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action action = null;


        if (actor.isConscious()) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        if (this.specialAttackEffect != null) {
                            action = new AttackAction(destination.getActor(), exit.getName(),this.specialAttackEffect);
                        } else {
                            action = new AttackAction(destination.getActor(), exit.getName());
                        }
                    }
                }
            }

            return action;
        } else {
            return null;
        }

    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Tatake...";
    }
}