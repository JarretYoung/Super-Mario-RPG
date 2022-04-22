package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.SpecialItem;

import java.security.DrbgParameters;

public abstract class ConsumeAction extends Action {
    private SpecialItem itemConsumed;
    private Location itemLocation;
    private String direction;

    public Location getItemLocation() {
        return itemLocation;
    }

    public SpecialItem getItemConsumed() {
        return itemConsumed;
    }

    public ConsumeAction(SpecialItem item, Location itemLocation, String direction)
    {
        this.itemConsumed = item;
        this.itemLocation = itemLocation;
        this.direction = direction;
    }
    @Override
    public abstract String execute(Actor actor, GameMap map);

    public String menuDescription(Actor actor) {
        return actor + " eats " + itemConsumed;
    }
}
