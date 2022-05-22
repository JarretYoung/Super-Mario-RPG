package game.actions;

import edu.monash.fit2099.demo.mars.grounds.Floor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.surfaces.Chest;

public class OpenChestAction extends Action {

    /**
     * The Actor that is to be talked to
     */
    protected Chest target;

    /**
     * The location of the chest
     */
    protected Location location;

    /**
     * Constructor for the TalkAction
     *
     * @param target is the actor that is meant to be talked to
     */
    public OpenChestAction(Chest target, Location location) {
        this.target = target;
        this.location = location;
    }

    /** Is the method that is meant to execute the open chest action
     *
     * This method opens a target chest
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String statement which carries a message to the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String output = this.target.dropItems(this.location);
        location.setGround(new Floor());
        return output;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens Chest";
    }
}
