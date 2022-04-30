package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.behaviours.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enemies extends Actor implements Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    /**
     * Constructor.
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name,displayChar,hitPoints);

        // Adding standard behaviours to the enemy
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(9, new AttackBehaviour());


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
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if ((otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) && !this.hasCapability(Status.KOOPA_DORMANT)) {
            actions.add(new AttackAction(this,direction));
        }
        if (this.hasCapability(Status.KOOPA_DORMANT)) {
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

        if (this.hasCapability(Status.RESET_QUEUED)) {
            map.removeActor(this);

            // To be changed for string output is not appropriate
            return new DoNothingAction();
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

    public Map<Integer, Behaviour> getBehaviour() {
        return this.behaviours;
    }

    /** This method is used to finish off an enemy with a shell during its dormant phase
     *
     */
    public void destroyShell() {
        super.hurt(super.getMaxHp()); //if not then change to number
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}