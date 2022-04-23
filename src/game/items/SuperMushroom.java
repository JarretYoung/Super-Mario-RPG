package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class SuperMushroom extends SpecialItem {
    /***
     * Constructor
     */
    public SuperMushroom(Actor actor) {
        super("Super Mushroom", '^');
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
    }
}
