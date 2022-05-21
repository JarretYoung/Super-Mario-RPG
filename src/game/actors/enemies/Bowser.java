package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.items.HandCuffKey;
import game.items.SuperMushroom;
import game.reset.Resettable;

import java.util.ArrayList;

public class Bowser extends Enemy implements Resettable {

    /**
     * Bowsers origninal spawn location. this is set.
     */
    private final Location SpawnLocation;

    /**
     * Constructor for the Bowser class
     *
     * @param spawn_x_coord is the x_coordinate where the Bowser is meant to be spawned
     * @param spawn_y_coord is the y_coordinate where the Bowser is meant to be spawned
     */
    public Bowser(GameMap map, int spawn_x_coord, int spawn_y_coord) {
        super("Bowser", 'B', 200);

        // add key to Bowser's inventory
        this.addItemToInventory(new HandCuffKey());
        this.addCapability(Status.DROP_FIRE_WHEN_ATTACK);
        this.getBehaviour().put(9, new AttackBehaviour());

        // Instantiating the spawn location of this Actor
        SpawnLocation = new Location(map, spawn_x_coord, spawn_y_coord);
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {

            if (!this.SpawnLocation.containsAnActor()){
                // Relocate this actor to their spawn location
                map.moveActor(this, SpawnLocation);
                // Heals this Actor
                this.heal(this.getMaxHp());
            }


            //Remove Cripple debuff
            if (this.hasCapability(Status.CRIPPLED)) {
                // This line is to clear off all statuses
                this.removeCapability(Status.CRIPPLED);
            }

            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }

            // This line is to ensure that the RESET_QUEUED status is removed
            this.removeCapability(Status.RESET_QUEUED);

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        if (!this.hasCapability(Status.CRIPPLED))
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
            }
        else {
            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviour().values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }
}
