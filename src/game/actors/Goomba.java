package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;
import game.actions.AttackAction;
import game.actions.ResetAction;
import game.behaviours.*;
import game.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemies implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviour().put(7, new SuicideBehaviour(this));
	}


}