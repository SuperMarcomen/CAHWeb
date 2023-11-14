'use strict';

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
    stompClient.send("/game/choose_decks/game_id/" + sessionStorage.getItem("game_uuid"), {}, JSON.stringify({deckName, selected}));

}

export function loginWithUsername(username) {
    stompClient.subscribe("/user/queue/uuid", function(message) {
        const {playerUUID, gameUUID} = JSON.parse(message.body);
        stompClient.unsubscribe("/user/queue/uuid");
        sessionStorage.setItem("player_uuid", playerUUID);
        sessionStorage.setItem("game_uuid", gameUUID);
        sessionStorage.setItem("username", username);

        stompClient.subscribe('/game/choose_decks/game_id/' + gameUUID, function (response) {
            var message = JSON.parse(response.body);
            // Handle the received message
            console.log("Received message: " + message.content);
        });
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
