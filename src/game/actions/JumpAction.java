package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.HighGround;
import game.Status;

import java.util.Random;

public class JumpAction extends Action {

    private Actor jumper;
    /**
     * The Actor that is to be attacked
     */
    private HighGround target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Location of High Ground
     */
    private Location high_ground_location;

    private Random rand = new Random();

    public JumpAction(Actor jumper, HighGround target, String direction, Location location) {
        this.jumper = jumper;
        this.target = target;
        this.direction = direction;
        this.high_ground_location = location;
    }

    @Override
    public String execute(Actor jumper, GameMap map) {

        String coordinates = " (" + high_ground_location.x() + ", " + high_ground_location.y() + " )";
        String success = jumper + " is now standing on " + target.getName() + coordinates + ". Look at all these angry Goombas. o.o ";
        String fail = jumper + " slipped and hurt himself for " + target.getJumpDamagePoints() + " hitpoints";

        // else if actor has supermushroom jump with 100% + no damage
        if (jumper.hasCapability(Status.SUPER)){
            map.moveActor(jumper, high_ground_location);
            return success;
        }
        else {
            if ((rand.nextInt(100) <= target.getJumpSuccessRate())){
                map.moveActor(jumper, high_ground_location);
                return success;
            }
            else {
                jumper.hurt(target.getJumpDamagePoints());
                return fail;
            }
        }


    }



    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps onto " + direction + " " + target.getName() ;
    }
}
