package game;


/**
 *
 * Sprout extends Tree
 * @author Lup Hoong
 * @version 1.0 5/4/2022
 * comments:  all new methods
 */
public class Sprout extends Tree implements SpawnCapable{


    /**
     * Constructor
     */
    public Sprout() {
        super('+',0);

    }

    /**
     * Checks all actions taken in every tick
     */
    public void checkAction(){
        checkGrowth();
        spawn();
    }
    /**
     * Method:
     * Sprout grows into Sampling
     */
    public void checkGrowth() {
        if (super.getAge() % 9 == 0){
            // Sprout grow into Sampling: conversion from Sprout to Sampling
        }
    }

    /**
     * Method:
     * Spawn pattern:
     * 10% chance to spawn Goomba on its position in every turn.
     * If any actor stands on it, it cannot spawn Goomba.
     */
    public void spawn(){
        if (canSpawn()){
            spawn();
        }
    }

    /**
     * Checks if any actor standing on Sprout
     * @return true if no actor standing on Sprout. Can Spawn
     *         false if an actor is standing on Srpout. Cannot Spawn
     */
    public boolean canSpawn(){
        return true; //PLACEHOLDER for now
    }


}
