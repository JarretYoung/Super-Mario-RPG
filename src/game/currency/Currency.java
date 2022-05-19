package game.currency;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.PickUpCurrencyAction;
import game.actors.CurrencyCollector;
/**Class of Currency items
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 *
 */
public abstract class Currency extends Item {
    /**
     * Value of currency
     */
    private int value;

    /**
     *
     * @param name name of currency
     * @param displayChar display character of currency
     * @param value value of currency
     */
    public Currency(String name, char displayChar, int value)
    {
        super(name, displayChar, false);
        this.value = value;
        addAction(new PickUpCurrencyAction(this));
    }

    /**
     * Returns integer value of currency item
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Executes when PickUpCurrencyAction is performed on item
     * @param by actor that picks up currency
     * @return String to execute method in PickUpCurrencyAction
     */
    public String pickedUp(Actor by){
        CurrencyCollector actor = null;
        if(WalletManager.getInstance().getOwners().contains(by))
            actor = (CurrencyCollector) by;
        actor.getWallet().addToBalance(this.getValue());

        return actor + " has added " + this.getValue() + "$ to his balance";
    }
}
