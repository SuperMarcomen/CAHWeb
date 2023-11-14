package it.marcodemartino.cah.server.controller.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class StartGameObject {

    @JsonProperty("game_uuid")
    private final UUID gameUUID;
    @JsonProperty("chosen_decks")
    private final List<Short> chosenDecks;
}
