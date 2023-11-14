package it.marcodemartino.cah.server.config;

import it.marcodemartino.cah.server.game.MatchManager;
import it.marcodemartino.cah.server.game.cards.deck.builder.DiskDeckBuilder;
import it.marcodemartino.cah.server.game.cards.deck.builder.DeckBuilder;
import it.marcodemartino.cah.server.game.cards.deck.DeckRepository;
import it.marcodemartino.cah.server.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class DeckConfig {

    @Bean
    public DeckBuilder getDeckBuilder() {
        return new DiskDeckBuilder(Paths.get("cards.json"));
    }

    @Bean
    public DeckRepository getDeckRepository(DeckBuilder deckBuilder) {
        return deckBuilder.build();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public MatchManager getMatchManager(DeckRepository deckRepository) {
        return new MatchManager(deckRepository);
    }
}
