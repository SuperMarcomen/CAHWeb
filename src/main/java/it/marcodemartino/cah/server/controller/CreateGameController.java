package it.marcodemartino.cah.server.controller;

import it.marcodemartino.cah.server.controller.entities.StartGameObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CreateGameController {

    @PostMapping("/create_game")
    public String createGame(@RequestBody StartGameObject startGameObject) {
        System.out.println(startGameObject.getChosenDecks());
        return "Ok";
    }
}
