package it.marcodemartino.cah.server.game.cards;

import it.marcodemartino.cah.server.game.collections.RandomArrayList;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final String name;
    private final RandomArrayList<String> whiteCards;
    private final RandomArrayList<BlackCard> blackCards;
    private final boolean official;

    public Deck(String name, RandomArrayList<String> whiteCards, RandomArrayList<BlackCard> blackCards, boolean official) {
        this.name = name;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.official = official;
    }

    public Deck() {
        whiteCards = new RandomArrayList<>();
        blackCards = new RandomArrayList<>();
        name = "Custom";
        official = false;
    }

    public String getRandomWhiteCard() {
        return whiteCards.removeRandom();
    }

    public BlackCard getRandomBlackCard() {
        return blackCards.removeRandom();
    }

    public void addAll(Deck deck) {
        this.whiteCards.addAll(deck.getWhiteCards());
        this.blackCards.addAll(deck.getBlackCards());
    }

    public void addAllWhiteCards(List<String> whiteCards) {
        this.whiteCards.addAll(whiteCards);
    }

    public void addAllBlackCards(List<BlackCard> blackCards) {
        this.blackCards.addAll(blackCards);
    }

    public List<String> getWhiteCards() {
        return List.copyOf(whiteCards);
    }

    public int getWhiteCardsSize() {
        return whiteCards.size();
    }

    public List<BlackCard> getBlackCards() {
        List<BlackCard> newList = new ArrayList<>();
        for (BlackCard blackCard : blackCards) {
            BlackCard newBlackCard = new BlackCard(blackCard.getText(), blackCard.getNumberOfParameters());
            newList.add(newBlackCard);
        }
        return List.copyOf(newList);
    }

    public int getBlackCardsSize() {
        return blackCards.size();
    }

    public String getName() {
        return name;
    }

    public boolean isOfficial() {
        return official;
    }
}
