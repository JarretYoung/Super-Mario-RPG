package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    SUPER, // used when actor has 100% jumping success rate with no fall damage
    INVINCIBLE, // used to make actor immune, walk over walls and have infinite damage for 10 rounds
    RESET_AVAILABLE, // used to indicate if a reset was available
    CRIPPLE_WEAPON, // used to indicate if a weapon is able to cripple an enemy
    CRIPPLED, // used to apply a status effect on an actor to imply they cannot move
    RESET_QUEUED, // used to indicate if a reset was requested and this entity was queued for reset action
    HAS_WRENCH, // used to indicate if the player has a wrench
    HAS_BOTTLE, // used to indicate if the player has a bottle
    BUFFABLE, // used to indicate if an actor is buffable
    ACTIVE, // used to indicate if an Enemy is in an active state
    DORMANT, // used to indicate if an Enemy is in a dormant state
    HIGH_GROUND, // used by grounds which are high grounds
    CAN_JUMP_ONTO_HIGH_GROUND, // used by actors who can jump onto high ground
    NPC, // used for actors that dont attack the player
    FINAL_BOSS_CLEARED, // used to indicate that the final boss (Bowser has been defeated and the game can be ended)
    GAME_COMPLETE // used to indicate that the Princess is saved and the game is over
}
