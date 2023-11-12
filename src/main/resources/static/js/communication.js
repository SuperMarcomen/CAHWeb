'use strict';

var stompClient = null;
var username = "Marco";

connect();

export function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnect, onError);
}

function onConnect() {
    stompClient.subscribe("/user/queue/errors", function(message) {
        alert("Error " + message.body);
    });

    stompClient.subscribe("/user/queue/uuid", function(message) {
        alert("Message " + message.body);
    });

    stompClient.send('/game/login', {}, JSON.stringify({ username: username }));

    // temp
}

function loginWithUsername(username) {
    stompClient.send('/game/login', {}, JSON.stringify({ username: username }));
    //stompClient.uns('/game/login', {}, JSON.stringify({ username: username }));

}

function onError() {
    console.log("Error")
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);

}
