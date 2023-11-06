package it.marcodemartino.cah.server.game.cards.deck;

import lombok.Getter;

import java.util.*;

@Getter
public class DeckRepository {

    private final Map<Number, String> idToCardText;
    private final Map<Number, Deck> decks;
    private List<DeckInfo> deckInfos;
    
    public DeckRepository(Map<Number, String> idToCardText, Map<Number, Deck> decks) {
        this.idToCardText = idToCardText;
        this.decks = decks;
    }

    public List<DeckInfo> getDeckInfos() {
        return deckInfos;
    }

    public void initDeckInfos() {
        deckInfos = new ArrayList<>();
        for (Deck deck : decks.values()) {
            short whiteCardsAmount = (short) (deck.getStartBlackCardsId() - deck.getStartWhiteCardsId());
            short blackCardsAmount = (short) (deck.getEndAllCardsId() - deck.getStartBlackCardsId());
            deckInfos.add(new DeckInfo(deck.getName(), whiteCardsAmount, blackCardsAmount, deck.isOfficial()));
        }
    }
}
