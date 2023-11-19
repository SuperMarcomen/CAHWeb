package it.marcodemartino.cah.server.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChoosenDeckObject {

    private final Number deckId;
    private final boolean selected;
    private final String gameUUID;
}
