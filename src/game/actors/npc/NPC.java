package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.TalkAction;
/**Class of NPC actors
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 *
 */
public abstract class NPC extends Actor {

    /**
     * Constructor.
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     */
    public NPC(String name, char displayChar) {
        super(name, displayChar, 9999);
        this.addCapability(Status.NPC);
    }

    /**
     * returns talk action if actor can talk
     * @param otherActor the Actor talking to NPC
     * @param direction the direction of the NPC from the Actor
     * @param map the map that has the NPC actor
     * @return talk action
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return do nothing action
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
