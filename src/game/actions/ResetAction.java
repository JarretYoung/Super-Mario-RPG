package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.ResetManager;
import game.Resettable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Special Action for Resetting the map.
 */
public class ResetAction extends Action {

//    /**
//     * List of resettable items
//     */
//    private ArrayList<Resettable> itemsToBeReset = new ArrayList<>();

    /**
     * Constructor.
     */
    public ResetAction() {}

    //this.itemsToBeReset = ResetManager.getInstance()

    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager itemsToBeReset = ResetManager.getInstance();

        itemsToBeReset.run();

        String result = "Map has been reset";
        return result;
    }

    @Override
    public String hotkey() {
        return "r";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "reset the map";
    }
}

