package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.TalkAction;

public abstract class Npc extends Actor {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     */
    public Npc(String name, char displayChar) {
        super(name, displayChar, 9999);
        this.addCapability(Status.NPC);
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        actions.add(new TalkAction(otherActor));

        return actions;
    }
}
