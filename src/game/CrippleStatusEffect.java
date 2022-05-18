package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.behaviours.WanderBehaviour;

public class CrippleStatusEffect extends StatusEffects{

    /**
     * This is the constructor for the StatusEffect class
     *
     * @param duration is the duration (remaining) of the status effect
     * @param victim is the victim of the status effect
     */
    public CrippleStatusEffect(int duration, Actor victim) {
        super(Status.CRIPPLED, duration, victim);

        // Check to see if the victim is an Enemy
        if (victim instanceof Enemy) {
            // Checks to see if the Enemy has WandererBehaviour
            if (((Enemy) victim).getBehaviour().containsKey(10)) {
                // Removes WandererBehaviour
                ((Enemy) this.getVictim()).getBehaviour().remove(10);
            }
            // Checks to see if the Enemy have FollowBehavior
            if (((Enemy) victim).getBehaviour().containsKey(7)) {
                // Removes FollowBehaviour
                ((Enemy) this.getVictim()).getBehaviour().remove(7);
            }
        }
    }




}
