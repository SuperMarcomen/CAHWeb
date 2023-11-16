package it.marcodemartino.cah.server.game;

import it.marcodemartino.cah.server.controller.entities.StartGameObject;
import it.marcodemartino.cah.server.game.cards.deck.Deck;
import it.marcodemartino.cah.server.game.cards.deck.DeckRepository;
import it.marcodemartino.cah.server.game.collections.RandomArrayList;

import java.util.*;
import java.util.stream.IntStream;

public class MatchManager {

    private final Map<UUID, Match> uuidToMatch;
    private final Map<UUID, List<String>> deckNamesForMatches;
    private final DeckRepository deckRepository;

    public MatchManager(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
        uuidToMatch = new HashMap<>();
        deckNamesForMatches = new HashMap<>();
    }

    public List<String> getDecksForMatch(UUID gameUUID) {
        return deckNamesForMatches.get(gameUUID);
    }

    public void addDeckToMatch(UUID mathUUID, String deckName) {
        deckNamesForMatches.get(mathUUID).add(deckName);
    }

    public void removeDeckFromMatch(UUID mathUUID, String deckName) {
        deckNamesForMatches.get(mathUUID).remove(deckName);
    }

    public Match getMatchFromId(UUID matchUUID) {
        return uuidToMatch.get(matchUUID);
    }

    public UUID createMatch(UUID playerUUID) {
        Match match = new Match();
        match.addPlayer(playerUUID);
        UUID gameUUID = UUID.randomUUID();
        uuidToMatch.put(gameUUID, match);
        deckNamesForMatches.put(gameUUID, new ArrayList<>());
        return gameUUID;
    }

    public void startMatch(StartGameObject startGameObject) {
        Match match = uuidToMatch.get(startGameObject.getGameUUID());
        fillCardIds(startGameObject.getChosenDecks(), match.getWhiteCardsIds(), match.getBlackCardsIds());
    }

    private void fillCardIds(List<Short> deckIds, RandomArrayList<Number> whiteCardsIds, RandomArrayList<Number> blackCardsIds) {
        for (Short deckId : deckIds) {
            Deck deck = deckRepository.getDecks().get(deckId);
            IntStream whiteCardsRange = IntStream.range(deck.getStartWhiteCardsId(), deck.getStartBlackCardsId());
            IntStream blackCardsRange = IntStream.rangeClosed(deck.getStartBlackCardsId(), deck.getEndAllCardsId());
            whiteCardsRange.forEach(id -> whiteCardsIds.add((short) id));
            blackCardsRange.forEach(id -> blackCardsIds.add((short) id));
        }
    }
}
