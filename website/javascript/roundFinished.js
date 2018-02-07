// The JSON returned after we complete a round.
// Typically we get this after every complete round 
// incomplete rounds (i.e. waiting on human input) 
// Most important gameOver (game finished), deckSize of each player, and 
var endResults = {
    "gameOver": false, // if the game is finished
    "communalPile": 0,
    "players": [
        {
            "identity": 0,
            "deck": {
                "deckSize": 6,
                "empty": false,
                "full": false
            },
            "currentCard": {
                "description": "Idris",
                "cat": [
                    8,
                    2,
                    7,
                    10,
                    6
                ],
                "highest": 3
            },
            "name": "You",
            "human": true,
            "activeStatus": true
        },
        {
            "identity": 1,
            "deck": {
                "deckSize": 6,
                "empty": false,
                "full": false
            },
            "currentCard": {
                "description": "Avenger",
                "cat": [
                    2,
                    5,
                    4,
                    3,
                    2
                ],
                "highest": 1
            },
            "name": "Computer 1",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 2,
            "deck": {
                "deckSize": 11,
                "empty": false,
                "full": false
            },
            "currentCard": {
                "description": "350r",
                "cat": [
                    1,
                    9,
                    2,
                    3,
                    0
                ],
                "highest": 1
            },
            "name": "Computer 2",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 3,
            "deck": {
                "deckSize": 11,
                "empty": false,
                "full": false
            },
            "currentCard": {
                "description": "m50",
                "cat": [
                    1,
                    10,
                    2,
                    2,
                    0
                ],
                "highest": 1
            },
            "name": "Computer 3",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 4,
            "deck": {
                "deckSize": 6,
                "empty": false,
                "full": false
            },
            "currentCard": {
                "description": "Hurricane",
                "cat": [
                    2,
                    5,
                    3,
                    5,
                    0
                ],
                "highest": 1
            },
            "name": "Computer 4",
            "human": false,
            "activeStatus": true
        }
    ]
}