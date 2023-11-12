package it.marcodemartino.cah.server.controller.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class LoginObject {

    @JsonProperty("username")
    private final String username;

    @JsonCreator
    public LoginObject(@JsonProperty("username") String username) {
        this.username = username;
    }
}
