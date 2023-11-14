package it.marcodemartino.cah.server.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GameCreatedObject {

    private final UUID playerUUID;
    private final UUID gameUUID;
}
