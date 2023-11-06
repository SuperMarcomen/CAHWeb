package it.marcodemartino.cah.server.game.cards.deck;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeckInfo {

    private final String name;
    private final short whiteCardsAmount;
    private final short blackCardsAmount;
    private final boolean official;

}
