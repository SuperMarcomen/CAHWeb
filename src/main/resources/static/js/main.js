'use strict';

import {connect, loginWithUsername} from "./communication.js";
import {init} from "./choose_decks.js";

const nameInput = document.getElementById("name_input");
const createGameButton = document.getElementById("create_button");

const mainPage = document.getElementById("main_page");
const chooseDecksPage = document.getElementById("choose_decks_page");

createGameButton.onclick = function () {
    const username = nameInput.value;
    loginWithUsername(username);
    mainPage.style.display = 'none';
    chooseDecksPage.style.display = 'flex';
    init();
}

connect();

const urlParams = new URLSearchParams(window.location.search);
const gameUUID = urlParams.get("game_uuid");