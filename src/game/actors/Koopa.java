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
import game.behaviours.Behaviour;
import game.Status;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lup Hoong
 * @version 1.0 7/4/2022
 * comments: req 1 requires Koopa class, duplicated directly from original Goomba class given in game
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
        super("Koopa", 'K', 20); // Koopa hitpoints not specified, assume = 20
        this.behaviours.put(10, new WanderBehaviour());
        this.hitPoints_active = 20; //This the Koopa's hp when it is in an active state
        this.addCapability(Status.KOOPA_ACTIVE);

        // Registering instance as a resettable object
        this.registerInstance();

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
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        if (this.hasCapability(Status.KOOPA_DORMANT)) {
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
            this.removeCapability(Status.KOOPA_ACTIVE);
            this.addCapability(Status.KOOPA_DORMANT);
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
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        //Determine after properly implementing Task 3
    }
}
