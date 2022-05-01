package game.currency;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.WalletManager;
import game.actions.PickUpCurrencyAction;
import game.actors.CurrencyCollector;

public abstract class Currency extends Item {
    private int value;
    public Currency(String name, char displayChar, int value)
    {
        super(name, displayChar, true);
        this.value = value;
        this.getAllowableActions().add(new PickUpCurrencyAction(this));
    }

    public int getValue() {
        return value;
    }

    public String pickedUp(Actor by){
        CurrencyCollector actor = null;
        if(WalletManager.getInstance().getOwners().contains(by))
            actor = (CurrencyCollector) by;
        actor.getWallet().addToBalance(this.getValue());

        return actor + " has added " + this.getValue() + "$ to his balance";
    }
}
