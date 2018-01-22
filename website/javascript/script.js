var AiNum = 0;

function btnPress() {
    console.log("button pressed");
}

function redirect() {
    document.getElementById('ai-btn-group').addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

function getAiNum(chosenAi) {
    AiNum = Number(chosenAi);
    printAiCard();
}

function printAiCard() {
    console.log("ain " + AiNum);
    var card = document.getElementById("card-info");
    var cardList = [];
    for (var i = 0; i < AiNum; i++) {
        //card[i].id = "AI" + i;
        console.log(i);
        cardList.push(card.innerHTML);
    }
    card.setAttribute("style", "white-space:nowrap;");
    card.innerHTML = cardList.join(" ");

}

function highlightWinner(AIone) {
    document.getElementById(AIone).style.border = "5px solid green";
}

//var winCount = {"aiWins": 27};