package it.marcodemartino.cah.server.controller;

import it.marcodemartino.cah.server.controller.entities.ChoosenDeckObject;
import it.marcodemartino.cah.server.controller.entities.GameCreatedObject;
import it.marcodemartino.cah.server.game.MatchManager;
import it.marcodemartino.cah.server.users.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class ChatController {

    private final Logger logger = LogManager.getLogger(ChatController.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    private final MatchManager matchManager;

    public ChatController(SimpMessagingTemplate messagingTemplate, UserService userService, MatchManager matchManager) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
        this.matchManager = matchManager;
    }

    @MessageMapping("/choose_decks/game_id/{id}")
    public void sendMessage(@PathVariable String id, ChoosenDeckObject choosenDeckObject) {
        logger.info(choosenDeckObject.getDeckName(), choosenDeckObject.isSelected());
        messagingTemplate.convertAndSend("/game/choose_decks/game_id/id/" + id, choosenDeckObject);
    }

    @MessageMapping("/login")
    @SendToUser("/queue/uuid")
    public GameCreatedObject processMessageFromClient(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        UUID playerUUID = userService.addUser(username);
        UUID gameUUID = matchManager.createMatch(playerUUID);
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
