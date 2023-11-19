package it.marcodemartino.cah.server.game.cards.deck;

import lombok.Getter;

import java.util.*;
import java.util.Map.Entry;

@Getter
public class DeckRepository {

    private final Map<Number, String> idToCardText;
    private final Map<Number, Byte> idToNumberOfArguments;
    private final Map<Number, Deck> decks;
    // This is awful, but I don't want to parse and modify the json yet again to add an id to each deck
    private Map<String, Number> deckNameToId;
    private List<DeckInfo> deckInfos;

    public DeckRepository(Map<Number, String> idToCardText, Map<Number, Byte> idToNumberOfArguments, Map<Number, Deck> decks) {
        this.idToCardText = idToCardText;
        this.idToNumberOfArguments = idToNumberOfArguments;
        this.decks = decks;
    }

    public List<DeckInfo> getDeckInfos() {
        return deckInfos;
    }

    public void initDeckInfos() {
        deckInfos = new ArrayList<>();
        deckNameToId = new HashMap<>();

        for (Entry<Number, Deck> idDeckEntry : decks.entrySet()) {
            deckNameToId.put(idDeckEntry.getValue().getName(), idDeckEntry.getKey());
        }
        for (Deck deck : decks.values()) {
            short whiteCardsAmount = (short) (deck.getStartBlackCardsId() - deck.getStartWhiteCardsId());
            short blackCardsAmount = (short) (deck.getEndAllCardsId() - deck.getStartBlackCardsId());
            deckInfos.add(new DeckInfo(deckNameToId.get(deck.getName()), deck.getName(), whiteCardsAmount, blackCardsAmount, deck.isOfficial()));
        }
    }
}
