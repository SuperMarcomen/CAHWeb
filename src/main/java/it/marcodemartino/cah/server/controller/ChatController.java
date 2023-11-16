package it.marcodemartino.cah.server.controller;

import it.marcodemartino.cah.server.controller.entities.*;
import it.marcodemartino.cah.server.game.Match;
import it.marcodemartino.cah.server.game.MatchManager;
import it.marcodemartino.cah.server.users.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.UUID;

@Controller
public class ChatController {

    private final Logger logger = LogManager.getLogger(ChatController.class);
    private final UserService userService;
    private final MatchManager matchManager;

    public ChatController(SimpMessagingTemplate messagingTemplate, UserService userService, MatchManager matchManager) {
        this.userService = userService;
        this.matchManager = matchManager;
    }

    @MessageMapping("/choose_decks/game_id/{id}")
    @SendTo("/queue/choose_decks/game_id/{id}")
    public ChoosenDeckObject sendMessage(ChoosenDeckObject choosenDeckObject) {
        UUID mathUUID = UUID.fromString(choosenDeckObject.getGameUUID());
        if (choosenDeckObject.isSelected()) {
            matchManager.addDeckToMatch(mathUUID, choosenDeckObject.getDeckName());
        } else {
            matchManager.removeDeckFromMatch(mathUUID, choosenDeckObject.getDeckName());
        }
        logger.info(choosenDeckObject.getDeckName(), choosenDeckObject.isSelected());
        return choosenDeckObject;
    }

    @MessageMapping("/join")
    @SendToUser("/queue/join_result")
    public JoinResult processMessageFromClient(@Payload JoinRequest joinRequest, SimpMessageHeaderAccessor headerAccessor) {
        String username = joinRequest.getUsername();
        UUID playerUUID = userService.addUser(username);
        UUID matchUUID = UUID.fromString(joinRequest.getGameUUID());
        Match match = matchManager.getMatchFromId(matchUUID);
        if (match == null) {
            return new JoinResult(false, playerUUID, Collections.emptyList());
        }
        match.addPlayer(playerUUID);
        System.out.println(username);
        headerAccessor.getSessionAttributes().put("uuid", playerUUID.toString());
        headerAccessor.getSessionAttributes().put("game_uuid", joinRequest.getGameUUID());

        return new JoinResult(true, playerUUID, matchManager.getDecksForMatch(matchUUID));
    }

    @MessageMapping("/login")
    @SendToUser("/queue/uuid")
    public GameCreatedObject processMessageFromClient(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        UUID playerUUID = userService.addUser(username);
        UUID gameUUID = matchManager.createMatch(playerUUID);
        System.out.println("Game UUID: " + gameUUID.toString());
        System.out.println(username);
        headerAccessor.getSessionAttributes().put("uuid", playerUUID.toString());
        headerAccessor.getSessionAttributes().put("game_uuid", gameUUID);

        return new GameCreatedObject(playerUUID, gameUUID);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
