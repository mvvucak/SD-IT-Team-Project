// This JSON is returned when a new game is started. 
// Typically this data is used once per new game.
var myNewGameData = {
    "gameId": 1, // the game id 
    "identity": 0, // your id
    "numberPlayers": 5, 
    "players": [
        {
            "identity": 0,
            "deck": {
                "deckSize": 8,
                "empty": false,
                "full": false
            },
            "currentCard": null,
            "name": "You",
            "human": true,
            "activeStatus": true
        },
        {
            "identity": 1,
            "deck": {
                "deckSize": 8,
                "empty": false,
                "full": false
            },
            "currentCard": null,
            "name": "Computer 1",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 2,
            "deck": {
                "deckSize": 8,
                "empty": false,
                "full": false
            },
            "currentCard": null,
            "name": "Computer 2",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 3,
            "deck": {
                "deckSize": 8,
                "empty": false,
                "full": false
            },
            "currentCard": null,
            "name": "Computer 3",
            "human": false,
            "activeStatus": true
        },
        {
            "identity": 4,
            "deck": {
                "deckSize": 8,
                "empty": false,
                "full": false
            },
            "currentCard": null,
            "name": "Computer 4",
            "human": false,
            "activeStatus": true
        }
    ]
}