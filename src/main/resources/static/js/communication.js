'use strict';

import {init} from "./choose_decks.js";
import {gameUUID, setGameUUID} from "./main.js";

let stompClient = null;

export function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnect, onError);
}

function onConnect() {
    stompClient.subscribe("/user/queue/errors", function(message) {
        alert("Error " + message.body);
    });
}

export function broadcastDeckSelection(deckName, selected) {
    console.log("Broadcast sent");
    stompClient.send("/game/choose_decks/game_id/" + sessionStorage.getItem("game_uuid"), {}, JSON.stringify({deckName, selected, gameUUID}));
}

export function joinGameWithUsername(username) {
    stompClient.send('/game/join', {}, JSON.stringify({ username: username, gameUUID: gameUUID}));
    return stompClient.subscribe("/user/queue/join_result", function(message) {
        const {successful, playerUUID, selectedDecks} = JSON.parse(message.body);
        stompClient.unsubscribe("/user/queue/join_result");

        if (!successful) {
            alert("Game not found!");
            return false;
        }

        for (let deckName in selectedDecks) {
            const checkBox = document.getElementById(deckName);
            checkBox.checked = true;
        }
        sessionStorage.setItem("player_uuid", playerUUID);
        sessionStorage.setItem("username", username);
        const gameUUID = sessionStorage.getItem("game_uuid");

        stompClient.subscribe('/queue/choose_decks/game_id/' + gameUUID, function (response) {
            const {deckName, selected} = JSON.parse(response.body);
            const checkBox = document.getElementById(deckName);
            checkBox.checked = selected;
        });
        init();
        return true;
    });
}

export function loginWithUsername(username) {
    stompClient.subscribe("/user/queue/uuid", function(message) {
        console.log(JSON.parse(message.body));
        const {playerUUID, newGameUUID, selectedDecks} = JSON.parse(message.body);
        stompClient.unsubscribe("/user/queue/uuid");
        sessionStorage.setItem("player_uuid", playerUUID);
        sessionStorage.setItem("game_uuid", newGameUUID);
        sessionStorage.setItem("username", username);
        setGameUUID(newGameUUID);

        stompClient.subscribe('/queue/choose_decks/game_id/' + newGameUUID, function (response) {
            const {deckName, selected} = JSON.parse(response.body);
            const checkBox = document.getElementById(deckName);
            checkBox.checked = selected;
        });
        init();
    });
    stompClient.send('/game/login', {}, JSON.stringify({ username: username }));
}

function onError() {
    console.log("Error")
}

function onMessageReceived(payload) {
    const message = JSON.parse(payload.body);
    console.log(message);

}
