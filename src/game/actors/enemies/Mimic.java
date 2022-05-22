package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.actions.IncognitoAction;
import game.actions.OpenMimicAction;
import game.behaviours.*;
import game.items.RendingScissors;

/**
 * Mimic class that extends abstract class Enemy class
 *
 * This class is a mimic which acts as a chest until opened then attacks the player
 *
 * @author Garret Yong Shern Min
 * @version 2.0 20/5/2022
 */
public class Mimic extends Enemy{


    /**
     * Constructor.
     */
    public Mimic() {
        super("Mimic", 'G', 200);

        this.addCapability(Status.DORMANT);
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
        if ((this.hasCapability(Status.DORMANT))) {
            actions.add(new OpenMimicAction(this, direction));
        } else if (this.hasCapability(Status.ACTIVE)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.ACTIVE)) {
            this.setDisplayChar('R');
            this.getBehaviour().put(10, new WanderBehaviour());
            this.getBehaviour().put(8, new AttackBehaviour());
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
            }
        }

        if (!this.isConscious()){
            map.removeActor(this);
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new IncognitoAction();
    }

    /** Is a method to check if this Actor is conscious
     *
     * @return a boolean value to indicate if the Actor is conscious
     */
    @Override
    public boolean isConscious() {
        if (super.isConscious() == false) {
            this.addItemToInventory(new RendingScissors());
        }
        return super.isConscious();
    }

    /** Is a method to get the intrinsic weapon of this Enemy
     *
     * @return an instance of IntrinsicWeapon native to this Enemy
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "chomp");
    }

}
