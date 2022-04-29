package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

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
