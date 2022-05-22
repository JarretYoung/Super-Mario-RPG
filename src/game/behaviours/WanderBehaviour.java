package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.surfaces.HighGround;

/** This class represents the behaviour of wandering around aimlessly
 * @author Garret Yong Shern Min
 * @version 1.0 18/5/2022
 */
public class WanderBehaviour extends Action implements Behaviour {

	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 *
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		if (actor.isConscious()) {
			for (Exit exit : map.locationOf(actor).getExits()) {
				Location destination = exit.getDestination();
				if(actor.hasCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND) && destination.getGround().hasCapability(Status.HIGH_GROUND))
					actions.add(new JumpAction((HighGround) destination.getGround(), exit.getName(), destination));

				if (destination.canActorEnter(actor)) {
					actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
				}
			}
			if (!actions.isEmpty()) {
				return actions.get(random.nextInt(actions.size()));
			}
			else {
				return null;
			}
		} else {
			return null;
		}


	}

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}