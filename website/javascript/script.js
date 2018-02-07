var AiNum = 0;

function disabledBtns() {
    document.getElementById("btn-group").hidden = true;
}

function redirect() {
    document.getElementById("ai-btn-group").addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

function roundNum() {
    var round = 1;
    document.getElementById("roundHeader").innerHTML = "Round " + round;
    document.getElementById("playBtns").hidden = true;
}

// function displayCurrentPlayer() {
//     var player;
//     JSON.stringify(computerRound.round.playersInRound[i].currentCard.description);
//     document.getElementById("turnDisplay").innerHTML = "It is AI " + player + "'s turn";
// }

function getAiNum(chosenAi) {
    AiNum = Number(chosenAi);
    showOpponents();
    roundNum();
    document.getElementById("btn-group").hidden = false;
    //displayCurrentPlayer();
}


function showOpponents() {
var n = 1;
    for (var i = 0; i < AiNum; i++) {
        card = document.getElementById("aiNum"+ n);
        card.innerHTML="AI " + n + "<br>";
        n++;
    }
}

// function printAiCard() {
//     var n = 1;
//     for (var i = 0; i < AiNum; i++) {
//         card = document.getElementById("slot" + n);
//         card.innerHTML="<h5>spaceship name</h5><br><p>size #<br>speed #<br>range #<br>firepower #<br>cargo #</p>";
//         document.getElementById("slot" + n).innerHTML = card.innerHTML;
//         n++;
//     }
// }

// function aiTurn() {
//     if (highlightWinner() > 0) {
//         document.getElementById("btn-group").disabled = true;
//         document.getElementById("turnDisplay").innerHTML = "it is " + highlightWinner() + "'s turn";
//         document.getElementById("slot2").style.border = "5px solid green";
//     }
// }



function humanCardDetails() {
    var i = 0;
    var n = 1;
    var humanPlayer;
    var catNames = ["size", "speed", "range", "firepower", "cargo"];
    for (i = 0; i < 5; i++) {
        if (computerRound.round.playersInRound[i].human == true) {
            var humanDeck = JSON.stringify(computerRound.round.playersInRound[i].deck.deckSize);
            var humanShipName = JSON.stringify(computerRound.round.playersInRound[i].currentCard.description);
            console.log(humanShipName);
            document.getElementById("humanDeck").innerHTML = "there are " + humanDeck + " cards in your deck <br>";
            document.getElementById("humanShipName").innerHTML = "your ship is " + humanShipName;

            for (j = 0; j < 5; j++) {
                var humanCatVals = JSON.stringify(computerRound.round.playersInRound[i].currentCard.cat[j]);
                //console.log(humanCatVals);
                document.getElementById("cat" + n).innerHTML += catNames[j] + "<br>" + humanCatVals;
                n++;
            }

        }
    }
}

function humanCatChoice(chosenCat) {
    var humanChoice = chosenCat;
    console.log(chosenCat);
}


function cats() {
    var n = 1;
    var j;
    var i;
    var shipCatVal;
    var shipCatName;
    var shipName;
    var numOfShips = myNewGameData.numberPlayers;
    var catNames = ["size", "speed", "range", "firepower", "cargo"];
    var numCats = catNames.length;

    for (i = 0; i < numOfShips - 1; i++) { //-1 to exclude human
        shipName = JSON.stringify(computerRound.round.playersInRound[i].currentCard.description);
        //console.log(shipName);

        for (j = 0; j < numCats; j++) {
            shipCatName = catNames[j];
            shipCatVal = JSON.stringify(computerRound.round.playersInRound[i].currentCard.cat[j]);
            //console.log(shipCatVal);
            document.getElementById("slot" + n).innerHTML += shipCatName + " " + shipCatVal + "<br>";
        }

        document.getElementById("aiNum" + n).innerHTML += shipName + "<br>";
        // document.getElementById("slot1").innerHTML = "SIZE" + "<br>" + shipSize;
        // document.getElementById("speed").innerHTML = "SPEED" + "<br>" + shipSpeed;
        n++;
    }
}

function aiPileDetails() {
    var n = 1;
    var handCards;
    var i = 0;
    for (i = 0; i < 4; i++) {
        handCards = JSON.stringify(computerRound.round.playersInRound[i].deck.deckSize);
        document.getElementById("deck" + n).innerHTML += "currently has " + handCards + " cards";
        n++;
    }
}

function comPileDetails() {
    var comPile;
    comPile = Number(JSON.stringify(computerRound.round.resultStatus));
    if (comPile > 1 || comPile == 0) {
        document.getElementById("comPile").innerHTML += "there are " + comPile + " cards in the communal pile";
    } else if (comPile == 1) {
        document.getElementById("comPile").innerHTML += "there is " + comPile + " card in the communal pile";
    }
}

function displayWinner() {
    var getWinner = Number(JSON.stringify(computerRound.round.winner.identity));
    console.log(getWinner);
    document.getElementById("slot" + getWinner).style.background = "green";
}


function nextRound() {
    document.getElementById("aa").textContent = "NEXT ROUND";
}




//disable category buttons when AI chooses - so if highlightWinner returns an AI then disable
//no show winner button w/o first choosing category