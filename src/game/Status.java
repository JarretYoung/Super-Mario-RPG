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
    RESET_QUEUED, // used to indicate if a reset was requested and this entity was queued for reset action
    CRIPPLE_ATTACK, // used to indicate if the attack is able to cripple an enemy
    CRIPPLED, // used to apply a status effect on an actor to imply they cannot move
    INK_ATTACK, // used to indicate that an enemy can drop ink on the ground
    HEAL_OVER_TIME, // used to indicate an actor will heal over time
    HAS_WRENCH, // used to indicate if the player has a wrench
    HAS_BOTTLE, // used to indicate if the player has a bottle
    HAS_FULL_BOTTLE, // used to indicate player has full bottle
    BUFFABLE, // used to indicate if an actor is buffable
    FOUNTAIN, //used to indicate fountain
    ACTIVE, // used to indicate if an Enemy is in an active state
    DORMANT, // used to indicate if an Enemy is in a dormant state
    HIGH_GROUND, // used by grounds which are high grounds
    CAN_JUMP_ONTO_HIGH_GROUND, // used by actors who can jump onto high ground
    SWIMMABLE_ENEMY, // used to indicate swimmable enemies
    AMPHIBIOUS, // used to indicate player can eneter water without drowning
    DROWNABLE, // used to indicate player can't swim
    CHEST_CLOSED, // used to indicate closed chest
    NPC, // used for actors that don't attack the player
    FINAL_BOSS_CLEARED, // used to indicate that the final boss (Bowser has been defeated and the game can be ended)
    PLAYER, // used to indicate actor is Player
    TELEPORTATION_GROUND, // used to indicate teleportation grounds such as warp pipe
    GAME_COMPLETE // used to indicate that the Princess is saved and the game is over
}
