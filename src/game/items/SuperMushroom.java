package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class SuperMushroom extends SpecialItem {
    private final int MAX_HP_INCREASE = 50;

    /***
     * Constructor
     */
    public SuperMushroom() {

        super("Super Mushroom", '^');
        this.setValue(400);
        this.addToTradeManager();
    }

    @Override
    public String eatenFromGround(Actor by) {
        Actor actor = by;
        actor.increaseMaxHp(MAX_HP_INCREASE);
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
        return super.eatenFromGround(by);
    }

    @Override
    public String eatenFromInventory(Actor by) {
        Actor actor = by;
        actor.increaseMaxHp(MAX_HP_INCREASE);
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
        return super.eatenFromInventory(by);
    }
}
