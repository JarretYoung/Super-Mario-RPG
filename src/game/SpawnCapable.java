package game;

/**
 * Spawns enemies, implemented by entities (Sprout, Mature)
 * Condition: if an actor stands on this entity, it will not spawn.
 *
 *  * @author Lup Hoong
 *  * @version 1.0 5/4/2022
 */
public interface SpawnCapable {

    void spawn();

    /**
     * Checks if entity is allowed to spawn
     * @return True if NO actor is standing on entity that want to spawn.
     *         False is there is an actor standing on entity that want to spawn.
     */
    boolean canSpawn();



}
