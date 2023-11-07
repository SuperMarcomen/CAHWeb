'use strict';

var stompClient = null;
var username = "Marco";

console.log("Helo");
connect();

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnect, onError);
    console.log("Started");
}

function onConnect() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send('/app/chat.addUser', {}, JSON.stringify({sender: username, message: 'JOIN'}));
}

function onError() {
    console.log("Error")
}

function onMessageReceived() {

}
