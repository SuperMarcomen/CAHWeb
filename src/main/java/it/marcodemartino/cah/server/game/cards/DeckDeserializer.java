package it.marcodemartino.cah.server.game.cards;

import com.google.gson.*;
import it.marcodemartino.cah.server.game.collections.RandomArrayList;

import java.lang.reflect.Type;

public class DeckDeserializer implements JsonDeserializer<Deck> {
    @Override
    public Deck deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String deckName = json.getAsJsonObject().get("name").getAsString();
        boolean official = json.getAsJsonObject().get("official").getAsBoolean();
        // Deserialize white cards as plain strings
        JsonArray whiteArray = jsonObject.getAsJsonArray("white");
        RandomArrayList<String> whiteCards = new RandomArrayList<>();
        for (JsonElement whiteElement : whiteArray) {
            whiteCards.add(whiteElement.getAsJsonObject().get("text").getAsString());
        }

        // Deserialize black cards
        JsonArray blackArray = jsonObject.getAsJsonArray("black");
        RandomArrayList<BlackCard> blackCards = new RandomArrayList<>();
        for (JsonElement blackElement : blackArray) {
            JsonObject blackObj = blackElement.getAsJsonObject();
            BlackCard blackCard = new BlackCard(blackObj.get("text").getAsString(), blackObj.get("pick").getAsInt());
            blackCards.add(blackCard);
        }

        return new Deck(deckName, whiteCards, blackCards, official);
    }
}
