package game.currency;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;


/**
 * Coins
 * Capabilities: Picking up a coin should increase the Player's wallet balance. (have not implemented)
 *
 * @author Lup Hoong
 * @version 1.0 5/4/2022
 */
public class Coin extends Item {

    /**
     * indicates the value of Coin instance
     */
    private final int value;

    /**
     * Constructor
     * @param value of coin object
     */
    public Coin(int value) {
        super("coin", '$', true);
        this.value = value;
    }

    /**
     * Getter
     * @return amount
     */
    public int getAmount() {
        return value;
    }
}
