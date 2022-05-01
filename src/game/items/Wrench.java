package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actors.CurrencyCollector;

public class Wrench extends WeaponItem implements TradeableItem{
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

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String traded(CurrencyCollector customer) {
        CurrencyCollector actor = customer;
        String result = "";
        if(customer.getWallet().getBalance() >= this.getValue()) {
            result = actor + " " + "buys" + " " + this + " for " + this.getValue();
            customer.getWallet().removeBalance(this.getValue());
            actor.getInventory().add(this);
        }
        else
            result = actor + " does not have sufficient fund for " + this;
        return result;
    }
}
