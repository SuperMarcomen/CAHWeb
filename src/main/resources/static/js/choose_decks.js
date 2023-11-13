const decksForm = document.getElementById("choose_decks_form");

export function init() {

    const checkBoxContainer = document.createElement("div");
    checkBoxContainer.classList.add("checkbox_container");

    const inputCheckbox = document.createElement("input");
    inputCheckbox.type = "checkbox";
    inputCheckbox.classList.add("deck_checkbox");

    const label = document.createElement("label");

    fetch("/get_decks")
        .then((response) => response.json())
        .then((json) => {
            for (const key of Object.keys(json)) {
                const {name, whiteCardAmount, blackCardAmount, official} = json[key];
                inputCheckbox.id = name;
                inputCheckbox.name = name;
                label.htmlFor = name;
                label.innerText = name;

                const cloneContainer = checkBoxContainer.cloneNode(true);
                cloneContainer.appendChild(inputCheckbox.cloneNode(true));
                cloneContainer.appendChild(label.cloneNode(true));
                decksForm.appendChild(cloneContainer);
            }
        });
}