package game.currency;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.Resettable;

import java.sql.ResultSet;


/**
 * Coins
 * Capabilities: Picking up a coin should increase the Player's wallet balance. (have not implemented)
 *
 * @author Lup Hoong
 * @version 1.0 5/4/2022
 */
public class Coin extends Currency implements Resettable {


    /**
     * Constructor
     * @param value of coin object
     */
    public Coin(int value) {
        super("coin", '$', value);
        this.registerInstance();
    }

    @Override
    public void resetInstance() {
        //Find a way to remove this object from the ground
    }
}
