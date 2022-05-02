package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Toad;
import game.monologue.Monologue;
import game.monologue.Monologue_Toad;

/**
 * Special Action for communicating (talking) with other Actors.
 *
 * @author Garret Yong Shern Min
 */
public class TalkAction extends Action {

    /**
     * The Actor that is to be talked to
     */
    protected Actor target;

    /**
     * The Monologue that is about to be delivered
     */
    private Monologue monologue;

    /**
     * Constructor for the TalkAction
     *
     * @param target is the actor that is meant to be talked to
     */
    public TalkAction(Actor target) {
        this.target = target;
    }

    /** Is the method that is meant to execute the talk action
     *
     * This method creates dialogue based on the type of Actor is delivering the speech
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String statement which carries a message to the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (target instanceof Toad ) {
            monologue = new Monologue_Toad();
            String result = monologue.speak(target);
            return result;
        } else {
            return "yabadabadooooo";
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + target;
    }
}
