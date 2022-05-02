package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.currency.Coin;
import game.surfaces.Dirt;
import game.surfaces.HighGround;

import java.util.Random;

/**
 * Jump Action jumps onto high grounds
 */
public class JumpAction extends Action {

    /**
     * The High Ground to be jumped onto
     */
    private final HighGround target;

    /**
     * The direction of jump.
     */
    private final String direction;

    /**
     * Location of High Ground
     */
    private final Location high_ground_location;

    /**
     * constructor
     * @param target high ground object
     * @param direction direction
     * @param location locatino of high ground
     */
    public JumpAction(HighGround target, String direction, Location location) {
        this.target = target;
        this.direction = direction;
        this.high_ground_location = location;
    }

    /**
     * Execution for JumpAction
     * @param jumper actor that wants to jump onto high ground
     * @param map The map the actor is on.
     * @return message
     */
    @Override
    public String execute(Actor jumper, GameMap map) {
        String ret = ""; // return message for fail or succesful jumps
        Random rand = new Random();
        String coordinates = " (" + high_ground_location.x() + ", " + high_ground_location.y() + " )";
        String success = jumper + " is now standing on " + target.getName() + coordinates + ". Look at all these angry Goombas. o.o ";
        String fail = jumper + " slipped and hurt himself for " + target.getJumpDamagePoints() + " hitpoints";

        //if actor has supermushroom jump with 100% + no damage
        if (jumper.hasCapability(Status.SUPER)){
            map.moveActor(jumper, high_ground_location);
            ret = success;
        } // if actor is invincible, walk over and drop coin
        else if(jumper.hasCapability(Status.INVINCIBLE)){
            map.moveActor(jumper, high_ground_location);
            map.at(high_ground_location.x(), high_ground_location.y()).setGround(new Dirt());
            map.at(high_ground_location.x(), high_ground_location.y()).addItem(new Coin(5));
            ret = success;
        }
        else { // actor jump with a success rate
            if ((rand.nextInt(100) <= target.getJumpSuccessRate())){
                map.moveActor(jumper, high_ground_location);
                ret = success;
            }
            else { // failed jump, hurt jumper
                jumper.hurt(target.getJumpDamagePoints());
                ret = fail;
            }
        }
        return ret;

    }


    /**
     * Menu description shown to player
     * @param actor The actor performing the action.
     * @return String of option to perform action
     */

    @Override
    public String menuDescription(Actor actor) {
        String ret = ""; // return message
        if(!actor.hasCapability(Status.INVINCIBLE))
            ret = actor + " jumps onto " + direction + " " + target.getName() ;
        else
            ret = actor + " walks onto " + direction + " " + target.getName();
        return ret;
    }
}
