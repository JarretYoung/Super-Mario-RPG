package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Buffable;
import game.items.Bottle;
import game.surfaces.Fountain;

public class FillUpAction extends Action {

    private Fountain fountain;

    public FillUpAction(Fountain fountain){
        this.fountain = fountain;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle bottle = null;
        String ret = "";
        if(actor.hasCapability(Status.HAS_BOTTLE)) {
            for(int i = 0; i < actor.getInventory().size(); i++){
                if (actor.getInventory().get(i).hasCapability(Status.HAS_BOTTLE))
                    bottle = (Bottle) actor.getInventory().get(i);
            }
            ret = fountain.FilledUpFrom((Buffable) actor, bottle);
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills up bottle with " + fountain.peek();
    }
}
