<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="UTF-8">
  <title>Top Trumps</title>

    <link rel="stylesheet" href="/assets/css/bootstrap.css">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="/assets/javascript/TopTrumpsHttp.js"></script> 
    <script type="text/javascript" src="/assets/javascript/script.js"></script>

</head>

<body onload="onReady()">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div id="top-bar">
          <div>
            <div id="playBtns" class="btn-lg" > <!-- onclick="humanCardDetails();" -->
              <button id="1" class="btn-lg" onclick="getAiNum(this.id)">1 AI player</button>
              <button id="2" class="btn-lg" onclick="getAiNum(this.id)">2 AI players</button>
              <button id="3" class="btn-lg" onclick="getAiNum(this.id)">3 AI players</button>
              <button id="4" class="btn-lg" onclick="getAiNum(this.id)">4 AI players</button>
            </div>
          </div>
          <h1 id="roundHeader"></h1>
          <h2 id="turnDisplay"></h2>
          </div>
        </div>
      </div>

       <div class="tt-card"> 

        <div class="col-md-9">
          <div id="card-header">
            <h3 id="humanDeck"></h3>
            <h3 id="humanShipName"></h3>
          </div>
        </div>

          <div id="btn-group">
            <button class="btn-lg cat-choice" value="0" id="cat1" onclick="humanCatChoice(this.value)"></button>
            <button class="btn-lg cat-choice" value="1" id="cat2" onclick="humanCatChoice(this.value)"></button>
            <button class="btn-lg cat-choice" value="2" id="cat3" onclick="humanCatChoice(this.value)"></button>
            <button class="btn-lg cat-choice" value="3" id="cat4" onclick="humanCatChoice(this.value)"></button>
            <button class="btn-lg cat-choice" value="4" id="cat5" onclick="humanCatChoice(this.value)"></button>
          </div>

        </div>
        </div>

        <div class="grid-container">
          <div id="col-md-3">
              <div id="card-info" class="tt-card">
                <div class = "aiNum" id="aiNum"></div>
                <div id="slot"></div>
              </div>
              <br>
              <div id="deck"></div>
          </div>


          <div id="col-md-3">
              <div id="card-info" class="tt-card">
                <div class = "aiNum" id="aiNum"></div>
                <div id="slot"></div>
                </div>
              <br>
              <div id="deck"></div>
          </div>

          <div id="col-md-3">
              <div id="card-info" class="tt-card">
                <div class = "aiNum" id="aiNum"></div>
                <div id="slot"></div>
                </div>
              <br>
              <div id="deck"></div>
          </div>

          <div id="col-md-3">
              <div id="card-info" class="tt-card">
                <div class = "aiNum" id="aiNum"></div>
                <div id="slot"></div>
                </div>
              <br>
              <div id="deck"></div>
          </div>
        </div>

   <div class="col-md-12">
        <div id="rndCat">
        </div>
    </div>
    <div class="col-md-12">
        <div id="comPile">
        </div>
    </div>

    <div class="row">
        <div id="round-end">
          <button class="btn-lg" id="aa" onclick="nextRound();">BEGIN</button>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div id="stats-btn">
            <button class="btn-lg" onclick="window.location.href='/toptrumps/stats/'">VIEW PAST GAME STATISTICS</button>
          </div>
        </div>
      </div>
  </body>
</html>