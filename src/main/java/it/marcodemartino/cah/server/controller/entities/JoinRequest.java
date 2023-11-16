package it.marcodemartino.cah.server.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinRequest {

    private final String username;
    private final String gameUUID;
}
