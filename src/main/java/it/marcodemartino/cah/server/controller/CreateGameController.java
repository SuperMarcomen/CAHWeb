package it.marcodemartino.cah.server.controller;

import it.marcodemartino.cah.server.controller.entities.CreateGameObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CreateGameController {

    @PostMapping("/create_game")
    public String createGame(@RequestBody CreateGameObject createGameObject) {
        System.out.println(createGameObject.getPlayerName());
        System.out.println(createGameObject.getChosenDecks());
        return "Ok";
    }
}
