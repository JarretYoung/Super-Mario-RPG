package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Buffable;
import game.items.WaterStorage;

public class DrinkAction extends Action {
    private WaterStorage source;
    private Actor drinkedBy;

    public DrinkAction(WaterStorage from, Actor by) {
        this.source = from;
        this.drinkedBy = by;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.drinkedBy = actor;
        String str = "";
        if(actor.hasCapability(Status.BUFFABLE) && !actor.hasCapability(Status.PLAYER))
            str =  source.DrinkedFrom((Buffable) actor);
        return str;
    }

    @Override
    public String menuDescription(Actor actor) {
        return drinkedBy + " drinks from " + source;
    }
}
