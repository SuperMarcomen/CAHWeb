import {broadcastDeckSelection} from "./communication.js";

const decksForm = document.getElementById("choose_decks_form");
const gameIdText = document.getElementById("game_id_text");
const startButton = document.getElementById("start_game_button");
var selectedDecks;

export function setSelectedDecks(newSelectedDecks) {
    selectedDecks = newSelectedDecks;
}


startButton.onclick = function () {

}

export function init() {
    const url = new URL(window.location.href);
    const urlParams = new URLSearchParams(window.location.search);
    if (!urlParams.get("game_uuid")) {
        url.searchParams.append("game_uuid", sessionStorage.getItem("game_uuid"));
    }
    gameIdText.textContent = "Share this link with your friends to join the game! " + url.href;

    fetch("/get_decks")
        .then((response) => response.json())
        .then((json) => {
            for (const key of Object.keys(json)) {
                const {id, name, whiteCardAmount, blackCardAmount, official} = json[key];

                // Create a new inputCheckbox and label for each deck
                const inputCheckbox = document.createElement("input");
                inputCheckbox.type = "checkbox";
                inputCheckbox.name = name;
                inputCheckbox.id = 'deck_' + id;
                inputCheckbox.classList.add("deck_checkbox");

                const label = document.createElement("label");
                label.htmlFor = 'deck_' + id;
                label.innerText = name;

                inputCheckbox.addEventListener('change', e => {
                    broadcastDeckSelection(id, e.target.checked);
                });

                const cloneContainer = document.createElement("div");
                cloneContainer.classList.add("checkbox_container");
                cloneContainer.appendChild(inputCheckbox);
                cloneContainer.appendChild(label);

                decksForm.appendChild(cloneContainer);
            }

            if (!selectedDecks) return;
            for (let deckId of selectedDecks) {
                console.log(deckId);
                const checkBox = document.getElementById('deck_' + deckId);
                checkBox.checked = true;
            }
        });
}