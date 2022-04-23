package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.SpecialItem;

public class ConsumeAction extends Action {
    private SpecialItem itemConsumed;

    public SpecialItem getItemConsumed() {
        return itemConsumed;
    }

    public ConsumeAction(SpecialItem item)
    {
        this.itemConsumed = item;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        if(map.locationOf(actor).getItems().contains(itemConsumed)) {
            ret = itemConsumed.eatenFromGround(actor);
            Location location = map.locationOf(actor);
            location.removeItem(itemConsumed);
        } else if(actor.getInventory().contains(itemConsumed)) {
            ret = itemConsumed.eatenFromInventory(actor);
        }
        return ret;
    }

    public String menuDescription(Actor actor) {
        return actor + " eats " + itemConsumed;
    }
}
