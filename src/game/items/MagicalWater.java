package game.items;

import game.actors.Buffable;
import game.surfaces.Fountain;

public class MagicalWater {
    private Fountain source;

    public void setSource(Fountain source) {
        this.source = source;
    }

    public Fountain getSource() { return source; }

    public MagicalWater(Fountain fountain){
        setSource(fountain);
    }

    public String drinked(Buffable actor){
        String ret = "";
        if(source.getName().equals("Health Fountain")) {
            actor.heal(50);
            ret = ", " + actor + " has healed 50 HP";
        }
        else if(source.getName().equals("Power Fountain")) {
            actor.addDamage(15);
            ret = ", " + actor + " has increased intrinsic damage by 15";
        }
        return ret;
    }

    public String toString(){
        String ret = "";
        if(source.getName().equals("Health Fountain"))
            ret = "Health Water";
        else if(source.getName().equals("Power Fountain"))
            ret = "Power Water";
        return ret;
    }
}
