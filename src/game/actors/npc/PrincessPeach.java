package game.actors.npc;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.EndGameAction;

/**
 * PrincessPeach class that extends abstract class Enemy class
 *
 * This class is the NPC which canonically the player is trying to save to end the game
 *
 * @author Garret Yong Shern Min
 * @version 2.0 19/5/2022
 */
public class PrincessPeach extends NPC {
    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P');
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.FINAL_BOSS_CLEARED)) {
            actions.add(new EndGameAction(this));
        }
        return actions;
    }
}
