package it.marcodemartino.cah.server.controller;

import it.marcodemartino.cah.server.controller.entities.LoginObject;
import it.marcodemartino.cah.server.users.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ChatController {

    private final Logger logger = LogManager.getLogger(ChatController.class);
    private final UserService userService;

    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/login")
    @SendToUser("/queue/uuid")
    public String processMessageFromClient(@Payload LoginObject loginObject, SimpMessageHeaderAccessor headerAccessor) {
        UUID uuid = userService.addUser(loginObject.getUsername());
        headerAccessor.getSessionAttributes().put("uuid", uuid.toString());
        return uuid.toString();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
