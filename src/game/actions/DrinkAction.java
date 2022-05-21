package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Buffable;
import game.items.WaterStorage;

public class DrinkAction extends Action {
    private WaterStorage source;

    public DrinkAction(WaterStorage from) {
        this.source = from;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String str = "";
        if(actor.hasCapability(Status.BUFFABLE) || actor.hasCapability(Status.HAS_BOTTLE))
            str =  source.DrinkedFrom((Buffable) actor);
        return str;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player drinks from " + source;
    }
}
