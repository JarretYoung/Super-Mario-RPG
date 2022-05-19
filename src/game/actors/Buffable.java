package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public interface Buffable {
    public int getDamage();
    public void addDamage(int addedDamage);
    public void heal(int points);
    public void makeBuffable();
}
