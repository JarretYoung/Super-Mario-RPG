package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import game.Status;

import java.util.ArrayList;
import java.util.Random;

/**This class represents the Monologue delivered by the Toad class to the player
 *
 * @author Garret Yong Shern Min
 */
public class Monologue {

    ArrayList<String> dialogue = new ArrayList<>();

    public Monologue() {
        this.dialogue.add("The Princess is depending on you! You are our only hope.");
        this.dialogue.add("Being imprisoned in these walls can drive a fungus crazy :(");
        this.dialogue.add("You might need a wrench to smash Koopa's hard shells.");
        this.dialogue.add("You better get back to finding the Power Stars.");

    }

    public String speak(Actor actor) {

        Random rand = new Random();

        int mode = 0;
        if ((actor.hasCapability(Status.HAS_WRENCH)) && (actor.hasCapability(Status.INVINCIBLE))){
            return (this.dialogue.get(rand.nextInt(2)));

        } else if (actor.hasCapability(Status.HAS_WRENCH)) {
            int i = rand.nextInt(4);
            while (i == 2) {
                i = rand.nextInt(4);
            }
            return (this.dialogue.get(i));

        } else if (actor.hasCapability(Status.INVINCIBLE)) {
            return (this.dialogue.get(rand.nextInt(3)));

        } else {
            return (this.dialogue.get(rand.nextInt(4)));

        }


    }
}
