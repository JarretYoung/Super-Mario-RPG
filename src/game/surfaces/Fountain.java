package game.surfaces;

import edu.monash.fit2099.engine.positions.Ground;
import game.items.WaterContainer;

public class Fountain extends Ground implements WaterContainer {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
    }
}
