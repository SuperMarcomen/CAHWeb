package it.marcodemartino.cah.server.controller;

import com.google.gson.Gson;
import it.marcodemartino.cah.server.game.cards.Deck;
import it.marcodemartino.cah.server.game.cards.DeckBuilder;
import it.marcodemartino.cah.server.json.GsonInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetDecksController {

    private final Map<String, Deck> decks;
    private final Gson gson;

    public GetDecksController(DeckBuilder deckBuilder) {
        this.decks = deckBuilder.build();
        gson = GsonInstance.get();
    }

    @GetMapping("/get_decks")
    public String getDecks() {
        return gson.toJson(decks);
    }
}
