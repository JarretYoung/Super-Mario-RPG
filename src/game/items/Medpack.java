package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * Medpack class that extends abstract class SpecialItem
 * Capabilities: Heals the player instantaneously by x amount
 * where x is a set amount of health
 *
 * @author Garret Yong
 * @version 2.0 19/5/2022
 */
public class Medpack extends EatAbleItem {
    /**
     * Value by which to increase consumers max HP
     */
    private int HealPoints = 70;

    /***
     * Constructor
     */
    public Medpack() {

        super("Medpack", 'H');
        this.setValue(350);
        this.addToTradeManager();
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
        actor.heal(this.HealPoints);
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
        actor.heal(this.HealPoints);
        return super.eatenFromInventory(by);
    }
}