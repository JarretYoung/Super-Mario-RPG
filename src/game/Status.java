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
    HAS_WRENCH, // used to indicate if the player has a wrench
    KOOPA_ACTIVE, // used to indicate if the Koopa is in an active state
    KOOPA_DORMANT, // used to indicate if the Koopa is in a dormant state
}
