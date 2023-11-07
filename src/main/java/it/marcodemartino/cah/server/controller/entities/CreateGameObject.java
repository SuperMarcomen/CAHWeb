package it.marcodemartino.cah.server.controller.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateGameObject {

    @JsonProperty("player_name")
    private final String playerName;
    @JsonProperty("chosen_decks")
    private final List<String> chosenDecks;
}
