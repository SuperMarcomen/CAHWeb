package it.marcodemartino.cah.server.game;

import it.marcodemartino.cah.server.game.collections.RandomArrayList;

public class Match {

    private final RandomArrayList<Number> whiteCardsIds;
    private final RandomArrayList<Number> blackCardsIds;

    public Match(RandomArrayList<Number> whiteCardsIds, RandomArrayList<Number> blackCardsIds) {
        this.whiteCardsIds = whiteCardsIds;
        this.blackCardsIds = blackCardsIds;
    }
}
