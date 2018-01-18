
function btnPress() {
    console.log("button pressed");
}

function redirect() {
    document.getElementById('ai-btn-group').addEventListener("click", function(){
        "window.location.href='main.html'"
    })
}

var AiNum = 0;

function getAiNum(chosenAI) {
    console.log(chosenAI);
    return chosenAI;
}

getAiNum();

// function getAiNum(){
//     if (document.getElementById("1p").addEventListener("click", function(){
//     console.log('1p');
//     AiNum = 1;
//     }));
//         console.log(AiNum);
//     return AiNum;
// }
    /*else (document.getElementById("2p").addEventListener("click", function(){
    console.log("2p") })
    })*/

for (var i = 0; i < AiNum; i++) {
    console.log("ai " + (i+1));
    //document.write();
}


/*function highlightWinner(AIone) {
    document.getElementById(AIone).style.border = "5px solid green";
}*/