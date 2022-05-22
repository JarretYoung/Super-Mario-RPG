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
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

/**
 * PiranhaPlant class that extends abstract class Enemy class
 *
 * This class is an Enemy which will spawn near warp pipes and attacks the Player should they come close
 *
 * @author Garret Yong Shern Min
 * @version 2.0 20/5/2022
 */
public class PiranhaPlant extends Enemy implements Resettable {
    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("PiranhaPlant", 'Y', 150);
        // Adding standard behaviours to the enemy
        this.getBehaviour().put(9, new AttackBehaviour());
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
        // If reset is queued then increase this Enemy's hp by 50 hit points and heal it to max hp
        if (this.hasCapability(Status.RESET_QUEUED)) {
            this.increaseMaxHp(50);
            this.heal(this.getMaxHp());

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        for(game.behaviours.Behaviour Behaviour : this.getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    /** This method is used to assign a new intrinsic weapon to the PiranhaPlant
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
