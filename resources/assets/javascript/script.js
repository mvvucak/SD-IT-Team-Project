    var AiNum = 0;
    var gameAjax = null;
    var rndWinner;
    var rndTies = [];
    var resultStatus;
    var numberPlayers;
    var gameOver;
    var gameWinner;
    var selectedCat;

    window.addEventListener("started-game", function(event) {
        document.getElementById("playBtns").hidden = true;
        numberPlayers = event.data.numberPlayers;
        insertIdtoCards(event.data.players);
        var endBtn = document.querySelector('#aa');
        endBtn.disabled = false;
        console.log("started-game");
    });

    window.addEventListener("started-round", function(event) {
        document.getElementById("btn-group").hidden = false;
        console.log(event.data);
        renderRound(event.data);
    });
    
    window.addEventListener("selected-category", function(event) {
        console.log(event.data);
        renderRound(event.data);
        showHideBtns('.cat-choice', true);
        showHideBtns('#aa', false);
    });
    
    window.addEventListener("end-results", function(event){
        console.log(event.data);
        gameOver = event.data.gameOver;
        gameWinner = event.data.gameWinner;
        openNextRound();
        displayResultCategory(selectedCat);
        cats(event.data.players);
        comPileDetails(event.data.communalPile);
        aiPileDetails(event.data.players);
    });

    window.addEventListener("saved-stats", function(event) {
        document.querySelector('#round-end').innerHTML = "Upload game stats successful!"
        console.log(event.data);
    });

function resetBoard() {
        var endBtn = document.querySelector('#aa');
        endBtn.textContent = "SHOW RESULTS";
        endBtn.setAttribute('onclick','displayWinner()');
        var catDiv = document.querySelector("#rndCat");
        catDiv.innerHTML = "Still to choose...";
        var elems = document.querySelectorAll(".tt-card");

        for(var i = 1; i < elems.length; i++) {
            elems[i].querySelector("#aiNum").innerHTML = "";
            elems[i].querySelector("#slot").innerHTML = "";
        }

        if(resultStatus != null && rndWinner != null)  {
            document.querySelector(".player-" + rndWinner.identity).style.background = "none";
        }

        for(i = 0; i < rndTies.length; i++) {
            var cardDiv = document.querySelector(".player-"+rndTies[i]);
            cardDiv.style.background = "none";
        }
}

function openNextRound() {
        var endBtn = document.querySelector('#aa');
        endBtn.textContent = "NEXT ROUND";
        endBtn.setAttribute('onclick','nextRound()');
        endBtn.disabled = false;
}


function showHideBtns(selector, option) {
    var elems = document.querySelectorAll(selector);
    if(elems == null) return;
    var selectorDisAttr = elems[0].hasAttribute("disabled");
    console.log(selectorDisAttr)
    if(selectorDisAttr && option) return;
    for(var i = 0; i < elems.length; i++) {
        elems[i].disabled = option;
    }
}

function onReady() {
    document.getElementById("btn-group").hidden = true;
    showHideBtns('.cat-choice', true);
    showHideBtns('#aa', true);
}

function redirect() {
    document.getElementById("ai-btn-group").addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

function roundNum(roundNumber) {
    var round = JSON.stringify(roundNumber);
    document.getElementById("roundHeader").innerHTML = "Round " + round;
}

function displayCurrentPlayer(name) {
    document.getElementById("turnDisplay").innerHTML = "It is " + name + "'s turn";
}

function getAiNum(chosenAi) {
    AiNum = Number(chosenAi);
    gameAjax = new TopTrumpsHttp(AiNum);
    gameAjax.startNewGame(chosenAi);
}

function renderRound(roundData) {
        var players = roundData.round.playersInRound;
        showOpponents(players);
        humanCardDetails(roundData);
        switchRoundPlayer(roundData);
        roundNum(roundData.round.roundNumber);
}

function switchRoundPlayer(roundData) {
    displayCurrentPlayer(roundData.round.startingPlayer.name);
    var option = !roundData.yourTurn;
    showHideBtns('.cat-choice', option);
    if(!roundData.yourTurn) {
        updateRoundResults(roundData.round);
    } else {
        showHideBtns("#aa", true);
    }
}

function updateRoundResults(round) {
    resultStatus = round.resultStatus;
    selectedCat = round.category;
    console.log("round status" + resultStatus);
    showHideBtns('#aa', false);
    if (resultStatus == 1) {
        rndWinner = round.winner;
    } else if(resultStatus == 0) {
        rndTies = [];
        var scores = round.playerScoreboard;
        var highestScore = scores[0].currentCard.cat[round.category];
        console.log(highestScore);
        rndTies.push(scores[0].identity);
        for(var i = 1; i < scores.length; i++) {
            console.log("draw loop run");
            if(scores[i].currentCard.cat[round.category] < highestScore) break;
            rndTies.push(scores[i].identity);
        }
        console.log("it was a draw boi");
    }
}

function displayResultCategory(cat) {
    var catNames = ["size", "speed", "range", "firepower", "cargo"];
    var catDiv = document.querySelector("#rndCat");
    catDiv.innerHTML = "The resulting category chosen was " + catNames[cat];
}

function showOpponents(opponents) {
    var n = 1;
    for (var i = 0; i < opponents.length; i++) {
        if(opponents[i].human == true) continue;
        var identity = opponents[i].identity;
        console.log(opponents[i]);
        var addedAi = opponents[i];
        var playerNode = document.querySelector(".player-"+identity);
        card = playerNode.querySelector("#aiNum");
        card.innerHTML= addedAi.name + "<br>";
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



function humanCardDetails(roundData) {
    var n = 1;
    var humanPlayer = roundData.round.playersInRound[0];
    var catNames = ["size", "speed", "range", "firepower", "cargo"];
    if (humanPlayer.human == true) {
            var humanShipName = JSON.stringify(humanPlayer.currentCard.description);
            console.log(humanShipName);
            document.getElementById("humanShipName").innerHTML = "your ship is " + humanShipName;
        for (i = 0; i <= 4; i++,  n++) {
                var humanCatVals = JSON.stringify(humanPlayer.currentCard.cat[i]);
                document.getElementById("cat" + n).innerHTML = catNames[i] + "<br>" + humanCatVals;
        }
    } else {
        document.querySelector("#btn-group").hidden = true;
    }
}

function humanCatChoice(chosenCat) {
    gameAjax.chooseCategory(chosenCat);
}


function cats(players) {
    var n = 1;
    var j;
    var i;
    var shipCatVal;
    var shipCatName;
    var shipName;
    var numOfShips = numberPlayers;
    var catNames = ["size", "speed", "range", "firepower", "cargo"];
    var numCats = catNames.length;

    for (i = 0; i < players.length; i++) { 
        if(players[i].human || players[i].activeStatus == false) continue;

        var identity = players[i].identity;
        var playerNode = document.querySelector(".player-"+identity);

        shipName = JSON.stringify(players[i].currentCard.description);
        for (j = 0; j < numCats; j++) {
            shipCatName = catNames[j];
            shipCatVal = JSON.stringify(players[i].currentCard.cat[j]);
            //document.getElementById("slot" + n).innerHTML += shipCatName + " " + shipCatVal + "<br>";
            playerNode.querySelector("#slot").innerHTML += shipCatName + " " + shipCatVal + "<br>";
        }

        //document.getElementById("aiNum" + n).innerHTML = shipName + "<br>";
        playerNode.querySelector("#aiNum").innerHTML = shipName + "<br>";
        n++;
    }
}

function humanCatBtnDisable() {
    var i;
    var n = 1;
    for (i = 0; i < 5; i++) {
        document.getElementById("cat" + n).disabled = true;
        n++;
    }
}

function aiPileDetails(opponentsDeck) {
    var n = 1;
    var handCards;
    for (i = 0; i < opponentsDeck.length; i++) {
        if(opponentsDeck[i].human) {
            var humanDeck = JSON.stringify(opponentsDeck[i].deck.deckSize);
            document.getElementById("humanDeck").innerHTML = "there are " + humanDeck + " cards in your deck <br>";
            continue;
        }
        var identity = opponentsDeck[i].identity;
        var playerNode = document.querySelector(".player-"+identity);

        handCards = JSON.stringify(opponentsDeck[i].deck.deckSize);
        parentDiv = playerNode.parentNode;
        parentDiv.querySelector("#deck").innerHTML = "currently has " + handCards + " cards";
        //document.getElementById("deck" + n).innerHTML = "currently has " + handCards + " cards";
        n++;
    }
}

function comPileDetails(comPile) {
    // var comPile;
    // comPile = Number(JSON.stringify(comput));
    if (comPile > 1 || comPile == 0) {
        document.getElementById("comPile").innerHTML = "there are " + comPile + " cards in the communal pile";
    } else if (comPile == 1) {
        document.getElementById("comPile").innerHTML = "there is " + comPile + " card in the communal pile";
    }
}

function displayWinner() {
    gameAjax.endResults();
    if(resultStatus == 1) {
            document.getElementById("turnDisplay").innerHTML = rndWinner.name + " won this round";
            document.querySelector(".player-" + rndWinner.identity).style.background = "green";
    } else {
        document.getElementById("turnDisplay").innerHTML = "It was a draw";
        for(i = 0; i < rndTies.length; i++) {
            var cardDiv = document.querySelector(".player-"+rndTies[i]);
            cardDiv.style.background = "orange";
        }
    }
}

function nextRound() {
    resetBoard();
    if(!gameOver) {
        gameAjax.startRound();
    } else {
        displayGameOver();
    }
    
}

function displayGameOver() {
        var endDiv = document.querySelector('#round-end');
        endDiv.innerHTML = "";
        var dbBtn = document.createElement("BUTTON");
        dbBtn.classList.add("btn-lg");
        var text = document.createTextNode("Save to database");
        dbBtn.appendChild(text);
        dbBtn.setAttribute('onclick','uploadGameStats()');
        var h4 = document.createElement("h4");
        h4.textContent = gameWinner.name + " won the game!";
        endDiv.appendChild(h4);
        endDiv.appendChild(dbBtn);
}

function uploadGameStats() {
    gameAjax.saveStatistics();
}

function insertIdtoCards(players) {
    var cards = document.querySelectorAll(".tt-card");
    for (var i = 0; i <= players.length - 1; i++) {
        cards[i].classList.add("player-" + players[i].identity);
    }
}