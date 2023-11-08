package it.marcodemartino.cah.server.game.cards.deck;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Deck {

    private final String name;
    private final short startWhiteCardsId;
    private final short startBlackCardsId;
    private final short endAllCardsId;
    private final boolean official;

    public int getWhiteCardAmount() {
        return startBlackCardsId - startWhiteCardsId + 1;
    }

    public int getBlackCardAmount() {
        return endAllCardsId - startBlackCardsId + 1;
    }
}
