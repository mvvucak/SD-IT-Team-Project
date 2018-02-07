// This is JSON returned every time a new round is started.
// Typically we pull this information upon starting every new round for the player
// There are two variables computerRound and humanRound 
// You can use each to test the difference between a your turn and the computer's turn 
// "category" refers to the category that got played during the rounds

var computerRound = {
    "yourTurn": false,
    "round": {
        "category": 1,
        "resultStatus": 1, // -1 (incomplete round) 0 (tie) 1 (win)
        "startingPlayer": {
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
            "activeStatus": true // true if still in it to win it
        },
        "winner": {
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
        "startingCard": {
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
        "winningCard": {
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
        "playersInRound": [
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
            },
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
            }
        ],
        "roundNumber": 2
    }
}

var humanRound = {
    "yourTurn": true,
    "round": {
        "category": 0,
        "resultStatus": -1,
        "startingPlayer": {
            "identity": 0,
            "deck": {
                "deckSize": 8,
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
            "name": "You",
            "human": true,
            "activeStatus": true
        },
        "winner": null,
        "startingCard": {
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
        "winningCard": null,
        "playersInRound": [
            {
                "identity": 0,
                "deck": {
                    "deckSize": 8,
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
                "name": "You",
                "human": true,
                "activeStatus": true
            },
            {
                "identity": 1,
                "deck": {
                    "deckSize": 18,
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
                "name": "Computer 1",
                "human": false,
                "activeStatus": true
            },
            {
                "identity": 2,
                "deck": {
                    "deckSize": 3,
                    "empty": false,
                    "full": false
                },
                "currentCard": {
                    "description": "Constellation",
                    "cat": [
                        4,
                        5,
                        7,
                        3,
                        4
                    ],
                    "highest": 2
                },
                "name": "Computer 2",
                "human": false,
                "activeStatus": true
            },
            {
                "identity": 3,
                "deck": {
                    "deckSize": 3,
                    "empty": false,
                    "full": false
                },
                "currentCard": {
                    "description": "Merchantman",
                    "cat": [
                        7,
                        3,
                        5,
                        6,
                        8
                    ],
                    "highest": 4
                },
                "name": "Computer 3",
                "human": false,
                "activeStatus": true
            },
            {
                "identity": 4,
                "deck": {
                    "deckSize": 3,
                    "empty": false,
                    "full": false
                },
                "currentCard": {
                    "description": "Hornet",
                    "cat": [
                        2,
                        5,
                        3,
                        4,
                        1
                    ],
                    "highest": 1
                },
                "name": "Computer 4",
                "human": false,
                "activeStatus": true
            }
        ],
        "roundNumber": 5
    }
}