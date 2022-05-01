package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.currency.Currency;

public class PickUpCurrencyAction extends Action {
    private Currency currencyItem;

    public PickUpCurrencyAction(Currency item){
        this.currencyItem = item;
    }

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

    @Override
    public String menuDescription(Actor actor) {
            return actor + " picks up " + currencyItem;
    }
}
