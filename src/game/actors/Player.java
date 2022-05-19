package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.reset.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.actions.ResetAction;


/**
 * Class representing the Player.
 *
 * @author Garret Yong Shern Min
 */
public class Player extends CurrencyCollector implements Resettable {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESET_AVAILABLE);
		this.addCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND);

		// Registering instance as a resettable object
		this.registerInstance();
	}

	@Override
	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();



		// Check Status
		if (this.hasCapability(Status.GAME_COMPLETE)) {
			map.removeActor(this);
		}

		if (this.hasCapability(Status.RESET_AVAILABLE)) {
			actions.add(new ResetAction());
		}

		// If reset is queued, this instance of player would be healed to max hp and all statuses will be purged
		if(this.hasCapability(Status.RESET_QUEUED)) {
			this.heal(getMaxHp());
			if ( this.hasCapability(Status.SUPER) ) {
				this.removeCapability(Status.SUPER);
			} else if (this.hasCapability(Status.INVINCIBLE)) {
				this.removeCapability(Status.INVINCIBLE);
			}

			this.removeCapability(Status.RESET_AVAILABLE);
			this.removeCapability(Status.RESET_QUEUED);
		}

		System.out.println(this + this.printHp() + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
		System.out.println("wallet: $" + this.getWallet().getBalance());
		System.out.println(this.getInventory());
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Gets display character of the actor, capital M if player is tall
	 * @return display character
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Returns a new collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}

		return actions;
	}

	/**
	 * Allows player to be reset
	 */
	@Override
	public void resetInstance() {
		this.addCapability(Status.RESET_QUEUED);
	}

}
