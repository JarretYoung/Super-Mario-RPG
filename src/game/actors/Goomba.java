package game.actors;


import game.Resettable;
import game.behaviours.*;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviour().put(7, new SuicideBehaviour(this));
	}


}