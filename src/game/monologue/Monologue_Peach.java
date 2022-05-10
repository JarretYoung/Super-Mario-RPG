package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public class Monologue_Peach extends Monologue{

    /** A constructor for the Monologue_Toad class
     *
     */
    public Monologue_Peach() {
        this.getDialogue().add("Thank you for saving me, the rightful ruler of the Fungi Kingdom");
    }

    @Override
    public String speak(Actor actor) {
        actor.addCapability(Status.GAME_COMPLETE);
        return this.getDialogue().get(0);
    }
}
