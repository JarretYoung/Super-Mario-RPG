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
 *
 * @author Lup Hoong
 * @version 1.0 21/4/2022
 */
public class JumpAction extends Action {

    /**
     * The High Ground to be jumped onto
     */
    private final HighGround TARGET;

    /**
     * The direction of jump.
     */
    private final String DIRECTION;

    /**
     * Location of High Ground
     */
    private final Location HIGH_GROUND_LOCATION;

    /**
     * constructor
     * @param target high ground object
     * @param direction direction
     * @param location locatino of high ground
     */
    public JumpAction(HighGround target, String direction, Location location) {
        this.TARGET = target;
        this.DIRECTION = direction;
        this.HIGH_GROUND_LOCATION = location;
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
        String coordinates = " (" + HIGH_GROUND_LOCATION.x() + ", " + HIGH_GROUND_LOCATION.y() + " )";
        String success = jumper + " is now standing on " + TARGET.getName() + coordinates + ". Look at all these angry Goombas. o.o ";
        String fail = jumper + " slipped and hurt himself for " + TARGET.getJumpDamagePoints() + " hitpoints";

        //if actor has supermushroom jump with 100% + no damage
        if (jumper.hasCapability(Status.SUPER)){
            map.moveActor(jumper, HIGH_GROUND_LOCATION);
            ret = success;
        } // if actor is invincible, walk over and drop coin
        else if(jumper.hasCapability(Status.INVINCIBLE)){
            map.moveActor(jumper, HIGH_GROUND_LOCATION);

            // to ensure that Warp Pipe does not get set to Dirt
            if (!this.TARGET.hasCapability(Status.TELEPORTATION_GROUND)){
                map.at(HIGH_GROUND_LOCATION.x(), HIGH_GROUND_LOCATION.y()).setGround(new Dirt());
            }

            map.at(HIGH_GROUND_LOCATION.x(), HIGH_GROUND_LOCATION.y()).addItem(new Coin(5));
            ret = success;
        }
        else { // actor jump with a success rate
            if ((rand.nextInt(100) <= TARGET.getJumpSuccessRate())){
                map.moveActor(jumper, HIGH_GROUND_LOCATION);
                ret = success;
            }
            else { // failed jump, hurt jumper
                jumper.hurt(TARGET.getJumpDamagePoints());
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
            ret = actor + " jumps onto " + DIRECTION + " " + TARGET.getName() ;
        else
            ret = actor + " walks onto " + DIRECTION + " " + TARGET.getName();
        return ret;
    }
}
