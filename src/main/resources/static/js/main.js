'use strict';

import {connect, loginWithUsername, joinGameWithUsername} from "./communication.js";

const nameInput = document.getElementById("name_input");
const createGameButton = document.getElementById("create_button");
const joinGameButton = document.getElementById("join_button");

const mainPage = document.getElementById("main_page");
const chooseDecksPage = document.getElementById("choose_decks_page");

const urlParams = new URLSearchParams(window.location.search);
export var gameUUID = urlParams.get("game_uuid");

export function setGameUUID(newGameUUID) {
    gameUUID = newGameUUID;
}

createGameButton.onclick = function () {
    const username = nameInput.value;
    loginWithUsername(username);
    mainPage.style.display = 'none';
    chooseDecksPage.style.display = 'flex';
}

joinGameButton.onclick = function () {
    const username = nameInput.value;
    if (joinGameWithUsername(username)) {
        mainPage.style.display = 'none';
        chooseDecksPage.style.display = 'flex';
    }

}
connect();

if (gameUUID) {
    sessionStorage.setItem("game_uuid", gameUUID);
    createGameButton.style.display = 'none';
    console.log(gameUUID);
}