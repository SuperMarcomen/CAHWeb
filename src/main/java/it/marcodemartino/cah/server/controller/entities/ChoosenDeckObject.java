package it.marcodemartino.cah.server.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChoosenDeckObject {

    private final String deckName;
    private final boolean selected;
}
