package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.SpecialItem;

public class ConsumeFromGroundAction extends ConsumeAction{
    public ConsumeFromGroundAction(SpecialItem item, Location itemLocation, String direction) {
        super(item, itemLocation, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return super.getItemConsumed().eatenFromGround(actor, super.getItemLocation());
    }
}
