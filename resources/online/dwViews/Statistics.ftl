<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="UTF-8">
  <title>Top Trumps</title>

  <!-- Bootstrap 4 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/assets/css/styles.css">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="/assets/javascript/TopTrumpsHttp.js"></script>

</head>

  <body>

  <!-- @ -36,8 +38,20 @@ -->
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div id="top-bar">
          <h1 id="totWins">Total # of wins</h1>
        </div>
      </div>
    </div>
  <div class="row">
    <div class="col-md-6">
      <h3>Human wins</h3>
        <p id="hWin"></p>
      <h3>AI wins</h3>
        <p id="aiWin"></p>
    </div>

    <div class="col-md-6">
      <h3>Average draws</h3>
        <p id="totDraws"></p>
      <h3>Most rounds</h3>
        <p id="numRounds"></p>
    </div>
</div>

</div>
  </div>
    <script type="text/javascript">
    var gameAjax = new TopTrumpsHttp();
    gameAjax.statistics();
    window.addEventListener("pulled-stats", function(event) {
        console.log(event.data);
        document.querySelector("#totDraws").innerHTML = event.data.averageDraws;
        document.querySelector("#numRounds").innerHTML = event.data.longestGame;
        document.querySelector("#aiWin").innerHTML = event.data.computerWins;
        document.querySelector("#hWin").innerHTML = event.data.humanWins;
    });
  </script>

  </body>
</html>