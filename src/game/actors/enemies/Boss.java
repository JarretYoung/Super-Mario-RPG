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
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Boss extends Actor implements Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    private ArrayList<Integer> SpawnLocation = new ArrayList<>(); // Spawn Location

    /**
     * Constructor.
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public Boss(String name, char displayChar, int hitPoints) {
        super(name,displayChar,hitPoints);

        //Adding standard attackBehavior
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // If reset is queued then remove this instance of enemy from this location
        if (this.hasCapability(Status.RESET_QUEUED)) {
            map.moveActor(this, new Location(map, this.SpawnLocation.get(0), this.SpawnLocation.get(1)));
            this.heal(this.getMaxHp());

            // To be changed for string output is not appropriate
            return new DoNothingAction();
        }

        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    this.behaviours.put(7, new FollowBehaviour(destination.getActor()));
                }
            }
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if ((action != null) && this.isConscious())
                return action;
        }
        return new DoNothingAction();
    }

    public ArrayList<Integer> getSpawnLocation() {
        return SpawnLocation;
    }

    public void setSpawnLocation(Integer XCoordinate, Integer YCoordinate) {
        SpawnLocation.set(0, XCoordinate);
        SpawnLocation.set(1, YCoordinate);
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
     * Method to queue a reset for all children extending the Tree class
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_QUEUED);
    }
}
