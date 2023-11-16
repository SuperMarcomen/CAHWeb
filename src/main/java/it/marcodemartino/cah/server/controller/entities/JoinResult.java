package it.marcodemartino.cah.server.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class JoinResult {

    private final boolean successful;
    private final UUID userUUID;
    private final List<String> selectedDecks;
}
