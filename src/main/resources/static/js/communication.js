'use strict';

var stompClient = null;
var username = "Marco";

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

export function loginWithUsername(username) {
    stompClient.subscribe("/user/queue/uuid", function(message) {
        stompClient.unsubscribe("/user/queue/uuid");
        sessionStorage.setItem("uuid", message);
        sessionStorage.setItem("username", username);
    });
    stompClient.send('/game/login', {}, JSON.stringify({ username: username }));
}

function onError() {
    console.log("Error")
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);

}
