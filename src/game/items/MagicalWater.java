package game.items;

import game.actors.Buffable;
import game.surfaces.Fountain;

public class MagicalWater {
    private Fountain source;

    public Fountain getSource() {
        return source;
    }

    public void setSource(Fountain source) {
        this.source = source;
    }

    public MagicalWater(Fountain fountain){
        setSource(fountain);
    }

//    public String drinked(Buffable actor){
//        if(source)
//    }
}
