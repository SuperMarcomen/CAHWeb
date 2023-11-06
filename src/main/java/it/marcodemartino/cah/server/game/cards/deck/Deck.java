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
}
