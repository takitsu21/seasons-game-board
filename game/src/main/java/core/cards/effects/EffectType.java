package core.cards.effects;

public enum EffectType {
    CRYSTAL, // Quand on veut cristaliser
    DRAW_CARD, // Quand on pioche une carte
    SEASON_CHANGE, // Lorsque l'on change de saison
    NEXT_TURN, // Une fois que tout les joueurs ont joués
    BEFORE_DICE, // Avant de récupérer les effets du dé on peut choisir le dé
    ON_SUMMON_CARD, // Lors de l'invocation d'une carte
    END_TURN, //à la fin de chaque manche (une fois que tous les joueurs ont joué)
    END_GAME, // à la fin de la partie on regarde si une carte de type end_game a été invoqué
    NONE;
}
