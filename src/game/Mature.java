package game;

public class Mature extends Tree implements SpawnCapable{

    public Mature() {
        super('T');
    }


    /**
     * Checks all actions taken in every tick
     */
    public void checkAction(){
        checkGrowth();
        spawn();
        growNewSproutRandomly();
    }

    /**
     * Method:
     * Mature has 20% to wither and die (becomes Dirt) in every turn.
     */
    public void checkGrowth() {
        //20% chance to wither and die every turn
    }

    /**
     * Method:
     * Spawn pattern: 15% chance to spawn Koopa in every turn. If an actor stands on it, it cannot spawn Koopa.
     *
     */
    public void spawn(){
        if (canSpawn()){
            spawn();
        }
    }

    /**
     * Checks if any actor standing on Mature
     * @return true if no actor standing on Mature. Can Spawn
     *         false if an actor is standing on Mature. Cannot Spawn
     */
    public boolean canSpawn(){
        return true; //PLACEHOLDER for now
    }

    /**
     * Method:
     * For every 5 turns
     * It can grow a new sprout (+) in one of the surrounding fertile squares, randomly.
     * If there is no available fertile square, it will stop growing sprouts
     * At the moment, the only fertile ground is Dirt
     */
    public void growNewSproutRandomly(){
        // every 5 turns, check for fertile squares
        // grow a new sprout (+) in one of the surrounding fertile squares

    }
}
