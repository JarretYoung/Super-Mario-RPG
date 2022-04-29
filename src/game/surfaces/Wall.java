package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.surfaces.HighGround;


public class Wall extends HighGround {

	public Wall() {
		super('#', "Wall",80, 20);
	}
	
//	@Override
//	public boolean canActorEnter(Actor actor) {
//		return false;
//	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
