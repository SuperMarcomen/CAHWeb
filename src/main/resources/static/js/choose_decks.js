import {broadcastDeckSelection} from "./communication.js";
import {gameUUID} from "./main.js";

const decksForm = document.getElementById("choose_decks_form");
const gameIdText = document.getElementById("game_id_text");

export function init() {
    const url = new URL(window.location.href)
    if (!gameUUID) {
        url.searchParams.append("game_uuid", sessionStorage.getItem("game_uuid"));
    }
    gameIdText.textContent = "Share this link with your friends to join the game! " + url.href;

    fetch("/get_decks")
        .then((response) => response.json())
        .then((json) => {
            for (const key of Object.keys(json)) {
                const {name, whiteCardAmount, blackCardAmount, official} = json[key];

                // Create a new inputCheckbox and label for each deck
                const inputCheckbox = document.createElement("input");
                inputCheckbox.type = "checkbox";
                inputCheckbox.name = name;
                inputCheckbox.id = name;
                inputCheckbox.classList.add("deck_checkbox");

                const label = document.createElement("label");
                label.htmlFor = name;
                label.innerText = name;

                inputCheckbox.addEventListener('change', e => {
                    broadcastDeckSelection(name, e.target.checked);
                    console.log("hi");
                });

                const cloneContainer = document.createElement("div");
                cloneContainer.classList.add("checkbox_container");
                cloneContainer.appendChild(inputCheckbox);
                cloneContainer.appendChild(label);

                decksForm.appendChild(cloneContainer);
            }
        });
}