package it.marcodemartino.cah.server.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatMessage {

    private final String sender;
    private final String message;
}
