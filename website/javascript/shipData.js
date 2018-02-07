
    // "options": {
    //  "aiAvailable": 4
    // },

    var globalData = {
    // "stats": [
    // {"overallGames": 100,"computerWins": 44,"humanWins": 22,"averageDraws": 33,
    // "mostAmountRounds": 66},
    // {"overallGames": 2,"computerWins": 87,"humanWins": 22,"averageDraws": 77,
    // "mostAmountRounds": 17},
    // {"overallGames": 3,"computerWins": 100,"humanWins": 33,"averageDraws": 32,
    // "mostAmountRounds": 21}],
    // "cat": [5, 7, 12, 4, 8],
    // // [3, 17, 4, 2, 8],
    // "desc": ["Avenger","Carrack"],
    // "catNames": ["size", "speed", "range", "firepower","cargo"],
    // }

    "round": {
    "catNames": ["size", "speed", "range", "firepower", "cargo"],
    "cards": [
        {
        "cat": [5, 7, 12, 4, 8],
        "desc": "avenger"
        },
        {
        "cat": [32, 3, 11, 9, 1],
        "desc": "carrack"
        },
        // {
        // "cat": [5, 7, 21, 2, 4],
        // "desc": "constellation"
        // }
    ]
    }
}

// function hi() {
//     var numOfShips = globalData.cat.length;
//     console.log(numOfShips);
// }

function cats() {
    var n = 1;
    var j;
    var i;
    var shipCatVal;
    var shipCatName;
    var shipName;
    var numOfShips = globalData.round.cards.length;
    var numCats = globalData.round.catNames.length;

    for (i = 0; i < numOfShips; i++) {
        shipName = JSON.stringify(globalData.round.cards[i].desc);

        for (j = 0; j < numCats; j++) {
            shipCatName = JSON.stringify(globalData.round.catNames[j]);
            shipCatVal = JSON.stringify(globalData.round.cards[i].cat[j]);
            document.getElementById("slot" + n).innerHTML += shipCatName + " " + shipCatVal + "<br>";
        }

        document.getElementById("aiNum" + n).innerHTML += shipName + "<br>";
        // document.getElementById("slot1").innerHTML = "SIZE" + "<br>" + shipSize;
        // document.getElementById("speed").innerHTML = "SPEED" + "<br>" + shipSpeed;
        n++;
    }
}

// function shipName() {
//     var name;
//     for (i = 0; i < numOfShips; i++) {
//     name = JSON.stringify(globalData.desc[i]);
//     console.log(name);
//     }
