
function btnPress() {
    console.log("button pressed");
}

function redirect() {
    document.getElementById('ai-btn-group').addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

var AiNum = 0;

function getAiNum(chosenAi) {
    AiNum = Number(chosenAi);
    printAiCard();
}

function printAiCard() {
    console.log("ain " + AiNum);
    var card = document.getElementById("card-info");
    for (var i = 0; i < AiNum; i++) {
        //card[i].id = "AI" + i;
        console.log(i);
        card.innerHTML += card.innerHTML;
    }
}

function highlightWinner(AIone) {
    document.getElementById(AIone).style.border = "5px solid green";
}

//var winCount = {"aiWins": 27};