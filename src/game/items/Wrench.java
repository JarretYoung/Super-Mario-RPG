package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actors.CurrencyCollector;
/**
 * Wrench class that extends abstract class SpecialItem
 * Capabilities: Lets consumer hit enemies with more damage and break Koopa shells
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 */
public class Wrench extends WeaponItem implements TradeableItem{
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

    /**
     * Traded method called when trade action is performed on wrench
     * @param customer actor that purchases wrench
     * @return String to execute method of result of item being traded
     */
    @Override
    public String traded(CurrencyCollector customer) {
        CurrencyCollector actor = customer;
        String result = "";
        if(customer.getWallet().getBalance() >= this.getValue()) {
            result = actor + " " + "buys" + " " + this + " for " + this.getValue();
            customer.getWallet().removeBalance(this.getValue());
            actor.addItemToInventory(this);
        }
        else
            result = actor + " does not have sufficient fund for " + this;
        return result;
    }
}
