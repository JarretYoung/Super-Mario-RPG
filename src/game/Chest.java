package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.JumpAction;
import game.actions.OpenChestAction;
import game.currency.Coin;
import game.items.PowerStar;

import java.util.Random;

public class Chest extends Ground {

    /**
     * High Ground's name
     */
    private final String name;

    /**
     * Constructor.
     */
    public Chest() {
        super('G');
        this.name = "Chest";
        this.addCapability(Status.CHEST_CLOSED);
    }

    /**
     * returns jump action if actor can jump
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return jump action
     */

    /** Returns OpenChestAction to allow the player to open the chests
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return OpenChestAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (this.hasCapability(Status.CHEST_CLOSED)) {
            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    actions.add(new OpenChestAction(this, location));
                }
            }
        }

//        if ( location.containsAnActor() ){
//            actions.add(new OpenChestAction(this, location));
//        }

        return actions;

    }

    /**
     * if actor can enter/ move into
     * @param actor the Actor to check
     * @return true false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Method to randomly drop an item
     * @param location
     * @return
     */
    public String dropItems(Location location) {
        Random rand = new Random();
        int chance = rand.nextInt(4);

        if (chance == 0) {
            // Drop a coin
            int value = (rand.nextInt(51) + 51);
            location.addItem(new Coin( value ));
            return "Chest dropped $" + value;
        } else if (chance == 1) {
            // Drop a syringe
            //Add code to drop a syringe
            return "Chest dropped a Syringe";
        } else if (chance == 2) {
            // Drop Medkit
            //Add code to drop medkit
            return "Chest dropped a Medkit";
        } else if (chance == 3) {
            // Drop a PowerStar
            location.addItem(new PowerStar());
            return "Chest dropped a Power Star";
        }

        this.setDisplayChar('E');
        this.removeCapability(Status.CHEST_CLOSED);
        return null;
    }
}
