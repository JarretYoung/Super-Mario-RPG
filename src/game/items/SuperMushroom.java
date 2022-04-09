package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class SuperMushroom extends SpecialItem {
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param actor
     */
    public SuperMushroom(Actor actor) {
        super("Super Mushroom", '^', actor);
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
    }
}
