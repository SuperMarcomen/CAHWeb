package it.marcodemartino.cah.server.game.cards;

import com.google.gson.annotations.SerializedName;

public class BlackCard {

    private final String text;
    @SerializedName("parameters")
    private final int numberOfParameters;

    public BlackCard(String text, int numberOfParameters) {
        this.text = text;
        this.numberOfParameters = numberOfParameters;
    }

    public String getText() {
        return text;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BlackCard other)) return false;
        return text.equals(other.getText()) && numberOfParameters == other.getNumberOfParameters();
    }
}
