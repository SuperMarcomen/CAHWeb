package it.marcodemartino.cah.server.game.cards.deck.builder;

import com.google.gson.Gson;
import it.marcodemartino.cah.server.game.cards.deck.DeckRepository;
import it.marcodemartino.cah.server.json.GsonInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class DiskDeckBuilder implements DeckBuilder {

    private static final Logger logger = LogManager.getLogger(DiskDeckBuilder.class);
    private final Path path;
    private final Gson gson;

    public DiskDeckBuilder(Path path) {
        this.path = path;
        this.gson = GsonInstance.get();
    }

    @Override
    public DeckRepository build() {
        try {
            String content = Files.readString(path);
            DeckRepository deckRepository = gson.fromJson(content, DeckRepository.class);
            deckRepository.initDeckInfos();
            logger.info("Loaded {} decks from disk", deckRepository.getDecks().size());
            return deckRepository;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DeckRepository(Collections.emptyMap(), Collections.emptyMap());
    }
}
