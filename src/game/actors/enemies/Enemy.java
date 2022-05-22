package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.reset.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.behaviours.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the generic class for an enemy class that would form the base for all enemies moving forwards
 *
 * @author Garret Yong Shern Min
 * @version 3.0 20/5/2022
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * This is the list of behaviours that the enemy harbours
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    /**
     * Constructor.
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name,displayChar,hitPoints);

        this.getBehaviour().put(8, new DrinkBehaviour());
        // Registering instance as a resettable object
        this.registerInstance();
    }


    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status #HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if ((otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) && !this.hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this,direction));
        }
        if (this.hasCapability(Status.DORMANT)) {
            actions.add(new BreakShellAction(this,direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {
            map.removeActor(this);

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }
        else if (!this.isConscious()){
            map.removeActor(this);
        }

        if (!this.hasCapability(Status.CRIPPLED)) {
            for (Exit exit : map.locationOf(this).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        this.getBehaviour().put(7, new FollowBehaviour(destination.getActor()));
                    }
                }
            }
        } else {
            // Checks to see if the Enemy have FollowBehavior
            if (this.getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                this.getBehaviour().remove(7);
            }
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }


    @Override
    public boolean isConscious() {
        return super.isConscious();
    }

    @Override
    public void increaseMaxHp(int points) {
        super.increaseMaxHp(points);
    }

    @Override
    public void resetMaxHp(int hitPoints) {
        super.resetMaxHp(hitPoints);
    }

    @Override
    public void heal(int points) {
        super.heal(points);
    }

    @Override
    public void hurt(int points) {
        super.hurt(points);
    }

    @Override
    public Weapon getWeapon() {
        return super.getWeapon();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public char getDisplayChar() {
        return super.getDisplayChar();
    }

    @Override
    public void addItemToInventory(Item item) {
        super.addItemToInventory(item);
    }

    @Override
    public void removeItemFromInventory(Item item) {
        super.removeItemFromInventory(item);
    }

    @Override
    public List<Item> getInventory() {
        return super.getInventory();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return super.getIntrinsicWeapon();
    }

    @Override
    public boolean hasCapability(Enum<?> capability) {
        return super.hasCapability(capability);
    }

    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(capability);
    }

    @Override
    public void removeCapability(Enum<?> capability) {
        super.removeCapability(capability);
    }

    @Override
    public List<Enum<?>> capabilitiesList() {
        return super.capabilitiesList();
    }

    /** This method is used to return the list of behaviours that is associated with this instance of enemy
     *
     * @return the list of behaviours that is associated with this instance of enemy
     */
    public Map<Integer, Behaviour> getBehaviour() {
        return this.behaviours;
    }

    /**
     * This method is used to finish off an enemy with a shell during its dormant phase
     */
    public void destroyShell() {
        super.hurt(super.getMaxHp()); //if not then change to number
    }

    /**
     * Method to queue a reset for all children extending the Enemy class
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}