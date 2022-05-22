package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.reset.Resettable;

/**
 * Shark class that extends abstract class Enemy class
 *
 * This class is a regular enemy that roams in the water
 *
 * @author Jastej Gill
 * @version 1.0 17/5/2022
 */
public class Shark extends Enemy{
    /**
     * Constructor.
     *
     */
    public Shark() {
        super("Shark", '>', 15);
        this.addCapability(Status.SWIMMABLE_ENEMY);
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
        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {

            // Heals this Actor
            this.heal(this.getMaxHp());

            //Remove Cripple debuff
            if (this.hasCapability(Status.CRIPPLED)) {
                // This line is to clear off all statuses
                this.removeCapability(Status.CRIPPLED);
            }

            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }

            // This line is to ensure that the RESET_QUEUED status is removed
            this.removeCapability(Status.RESET_QUEUED);

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        if (!this.hasCapability(Status.CRIPPLED))
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
                for (Exit exitOfExit : exit.getDestination().getExits()){
                    if (destination.containsAnActor()) {
                        Location destination2 = exitOfExit.getDestination();
                        if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                        }
                    }
                }
            }
        else {
            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    /** This method is used to assign a new intrinsic weapon to the Shark
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
