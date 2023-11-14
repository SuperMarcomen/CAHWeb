package it.marcodemartino.cah.server.game;

import it.marcodemartino.cah.server.game.collections.RandomArrayList;

import java.util.*;

public class Match {

    private final RandomArrayList<Number> whiteCardsIds;
    private final RandomArrayList<Number> blackCardsIds;
    private final List<UUID> playerUUIDs;

    public Match(RandomArrayList<Number> whiteCardsIds, RandomArrayList<Number> blackCardsIds) {
        this.whiteCardsIds = whiteCardsIds;
        this.blackCardsIds = blackCardsIds;
        playerUUIDs = new ArrayList<>();
    }

    public Match() {
        this.whiteCardsIds = new RandomArrayList<>();
        this.blackCardsIds = new RandomArrayList<>();
        playerUUIDs = new ArrayList<>();
    }

    public void addPlayer(UUID uuid) {
        playerUUIDs.add(uuid);
    }

    public RandomArrayList<Number> getWhiteCardsIds() {
        return whiteCardsIds;
    }

    public RandomArrayList<Number> getBlackCardsIds() {
        return blackCardsIds;
    }
}
