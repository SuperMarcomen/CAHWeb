package it.marcodemartino.cah.server.controller;

import com.google.gson.Gson;
import it.marcodemartino.cah.server.game.cards.deck.DeckRepository;
import it.marcodemartino.cah.server.json.GsonInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDecksController {

    private final DeckRepository deckRepository;
    private final Gson gson;

    public GetDecksController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
        gson = GsonInstance.get();
    }

    @GetMapping("/get_decks")
    public String getDecks() {
        return gson.toJson(deckRepository.getDeckInfos());
    }
}
