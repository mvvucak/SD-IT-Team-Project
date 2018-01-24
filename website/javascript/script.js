var AiNum = 0;

function btnPress() {
    console.log("button pressed");
}

function redirect() {
    document.getElementById('ai-btn-group').addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

function roundNum() {
    var round = 1;
    document.getElementById("roundHeader").innerHTML = "Round " + round;
    document.getElementById("playBtns").style.visibility="hidden";
}

function displayCurrentPlayer() {
    var player = 1;
    document.getElementById("turnDisplay").innerHTML = "It is AI " + player + "'s turn";
}

function getAiNum(chosenAi) {
    AiNum = Number(chosenAi);
    showOpponents();
    roundNum();
    displayCurrentPlayer();
}

function showOpponents() {
var n = 1;
    for (var i = 0; i < AiNum; i++) {
        card = document.getElementById("aiNum"+n);
        card.innerHTML="AI " + n;
        n++;
    }
}

function printAiCard() {
    var n = 1;
    for (var i = 0; i < AiNum; i++) {
        card = document.getElementById("slot" + n);
        card.innerHTML="<h5>spaceship name</h5><br><p>size #<br>speed #<br>range #<br>firepower #<br>cargo #</p>";
        document.getElementById("slot" + n).innerHTML = card.innerHTML;
        n++;
    }
}

// function aiTurn() {
//     if (highlightWinner() > 0) {
//         document.getElementById("btn-group").disabled = true;
//         document.getElementById("turnDisplay").innerHTML = "it is " + highlightWinner() + "'s turn";
//         document.getElementById("slot2").style.border = "5px solid green";
//     }
// }

function highlightWinner() {
     document.getElementById("slot2").style.background = "green";
     document.getElementsByClassName("btn-lg").disabled = true;
     //return 1;
}

function nextRound() {
    document.getElementById("aa").textContent = "NEXT ROUND";
}

//var winCount = {"aiWins": 27};

//disable category buttons when AI chooses - so if highlightWinner returns an AI then disable
//no show winner button w/o first choosing category