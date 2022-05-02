package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.currency.Currency;

/**
 * Action to pick up currency
 *  @author Jastej Gill
 *  @version 2.0 30/4/2022
 */
public class PickUpCurrencyAction extends Action {
    /**
     * currency picked up
     */
    private Currency currencyItem;

    /**
     * Constructor
     * @param item item picked up
     */
    public PickUpCurrencyAction(Currency item){
        this.currencyItem = item;
    }

    /**
     * method that runs when action is executed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of result of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        if (map.locationOf(actor).getItems().contains(currencyItem)) {
            ret = currencyItem.pickedUp(actor);
            Location location = map.locationOf(actor);
            location.removeItem(currencyItem);
        }
        return ret;
    }

    /**
     * method to display pick up currency option to actor
     * @param actor The actor performing the action.
     * @return String of menu option
     */
    @Override
    public String menuDescription(Actor actor) {
            return actor + " picks up " + currencyItem;
    }
}
