package it.marcodemartino.cah.server.config;

import it.marcodemartino.cah.server.game.cards.DeckBuilder;
import it.marcodemartino.cah.server.game.cards.DiskDeckBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class DeckConfig {

    @Bean
    public DeckBuilder getDeckBuilder() {
        return new DiskDeckBuilder(Paths.get("cah-cards-full.json"));
    }
}
