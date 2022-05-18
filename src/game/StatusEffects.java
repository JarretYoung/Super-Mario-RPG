package game;

import edu.monash.fit2099.engine.actors.Actor;

abstract class StatusEffects {

    /**
     * Associated status effect
     */
    protected Status statusEffectDescription;

    /**
     * Duration of the effect
     */
    protected int duration;

    /**
     * The victim of the status effect
     */
    private Actor victim;

    /** This is the constructor for the StatusEffect class
     *
     * @param statusEffectDescription is the Status enum for this status effect
     * @param duration is the duration (remaining) of the status effect
     */
    public StatusEffects(Status statusEffectDescription, int duration, Actor victim) {
        this.statusEffectDescription = statusEffectDescription;
        this.duration = duration;
        this.victim= victim;
    }

    public Status getStatusEffectDescription() {
        return statusEffectDescription;
    }

    public void setStatusEffectDescription(Status statusEffectDescription) {
        this.statusEffectDescription = statusEffectDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Actor getVictim() {
        return victim;
    }

    public void setVictim(Actor victim) {
        this.victim = victim;
    }

    public void tick() {
        this.duration -= 1;
    }

    public boolean statusExpired() {
        return this.duration <= 0;
    }
}
