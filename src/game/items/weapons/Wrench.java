package game.items.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actors.CurrencyCollector;
import game.items.TradeableItem;

/**
 * Wrench class that extends abstract class SpecialItem
 * Capabilities: Lets consumer hit enemies with more damage and break Koopa shells
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 */
public class Wrench extends WeaponItem implements TradeableItem {
    /**
     * Value of wrench
     */
    private int value;
    /**
     * Constructor.
     *
     */
    public Wrench() {

        super("wrench", '{', 50, "hits", 80);
        this.addCapability(Status.HAS_WRENCH);
        this.setValue(200);
        this.addToTradeManager();
    }

    /**
     * Accessor for value of wrench
     * @return integer value of wrench
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Mutator for value of wrench
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

}
