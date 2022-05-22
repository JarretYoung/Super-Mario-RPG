package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.currency.WalletManager;
import game.actors.CurrencyCollector;
import game.items.TradeableItem;
/**
 * Trade Action sells tradeable item
 *
 * @author Jastej Gill
 * @version 1.0 22/4/2022
 */
public class TradeAction extends Action {
    /**
     * Item traded
     */
    private TradeableItem item;

    /**
     * Constructor
     * @param item Item traded
     */
    public TradeAction(TradeableItem item) {
        this.item = item;
    }

    /**
     * Execution for TradeAction
     * @param actor actor buying special item
     * @param map The map the actor is on.
     * @return message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        CurrencyCollector customer = null;
        if(WalletManager.getInstance().getOwners().contains(actor))
            customer = (CurrencyCollector) actor;
        return item.traded(customer);
    }

    /**
     * Menu description shown to player
     * @param actor The actor performing the action.
     * @return String of option to perform action
     */
    @Override
    public String menuDescription(Actor actor) {
           return actor + " buys " + item + " for " +item.getValue() + "$";
    }
}
