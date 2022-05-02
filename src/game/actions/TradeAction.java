package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.currency.WalletManager;
import game.actors.CurrencyCollector;
import game.items.TradeableItem;

public class TradeAction extends Action {
    private Actor trader;
    private TradeableItem item;

    public TradeAction(Actor actor, TradeableItem item) {
        this.trader = actor;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        CurrencyCollector customer = null;
        if(WalletManager.getInstance().getOwners().contains(actor))
            customer = (CurrencyCollector) actor;
        return item.traded(customer);
    }

    @Override
    public String menuDescription(Actor actor) {
           return actor + " buys " + item + " for " +item.getValue() + "$";
    }
}
