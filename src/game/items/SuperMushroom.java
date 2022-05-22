package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
/**
 * SuperMushroom class that extends abstract class SpecialItem
 * Capabilities: Increases max HP by 50, allows actor to jump with 100% success rate
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 */
public class SuperMushroom extends EatAbleItem {
    /**
     * Value by which to increase consumers max HP
     */
    private final int MAX_HP_INCREASE = 50;

    /***
     * Constructor
     */
    public SuperMushroom(Boolean tradeable) {

        super("Super Mushroom", '^');
        this.setValue(400);
        if (tradeable == true) {
            this.addToTradeManager();
        }
    }

    /**
     * Method called when consume action is performed on super mushroom while item is on the ground
     * Adds status Tall and Super to actor that eats it and increases their max HP
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    @Override
    public String eatenFromGround(Actor by) {
        Actor actor = by;
        actor.increaseMaxHp(MAX_HP_INCREASE);
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
        return super.eatenFromGround(by);
    }

    /**
     * Method called when consume action is performed on super mushroom while item is iin inventory
     * Adds status Tall and Super to actor that eats it and increases their max HP
     * Removes item from inventory
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    @Override
    public String eatenFromInventory(Actor by) {
        Actor actor = by;
        actor.increaseMaxHp(MAX_HP_INCREASE);
        this.addCapability(Status.TALL);
        this.addCapability(Status.SUPER);
        return super.eatenFromInventory(by);
    }
}
