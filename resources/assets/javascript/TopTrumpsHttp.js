function TopTrumpsHttp() {
	this.gameid = sessionStorage.getItem("gameid") || null;

	this.init = function() {
		if (this.storageAvailable('sessionStorage')) {
  			if(this.gameid == null) {	  
  				//this.startNewGame(this.aiselect);
  			}
		} else {
			console.error("Sorry your browser is not supported.");
		}
	}

	this.startNewGame = function(aiSelect) {
		var self = this;
		aiChoice = aiSelect || 4;
		var event = new CustomEvent("started-game");
		var pathParams = ["game"];
		var queryParams = "?aiselect=" + aiChoice;
		var action = "POST";
		var gameReq = this.xhrSendReq(pathParams, queryParams, action);
		gameReq.onload = function(e) {
 			var newGameId = JSON.stringify(gameReq.response.gameId);
 			sessionStorage.setItem('gameid', newGameId);
 			self.gameid = gameReq.response.gameId;
 			console.log("gameid " + self.gameid);
 			event.data = gameReq.response;
			window.dispatchEvent(event);
		};
		gameReq.send(); 
	}

	this.startRound = function() {
				var event = new CustomEvent("started-round");
				console.log(this.gameid);
				var pathParams = ["game", this.gameid, 'new_round'];
				var queryParams = "";
				var action = "GET";
				var roundReq = this.xhrSendReq(pathParams, queryParams, action);
				roundReq.onload = function(e) {
 					event.data = roundReq.response;
					window.dispatchEvent(event);
				};
				roundReq.send();
	}

	this.chooseCategory = function(catChoice) {
				var event = new CustomEvent("selected-category");
				var pathParams = ["game", this.gameid, "select"];
				var queryParams = "?category=" + catChoice;
				var action = "POST"
				var catReq = this.xhrSendReq(pathParams, queryParams, action);
				catReq.onload = function(e) {
 					event.data = catReq.response;
					window.dispatchEvent(event);
				};
				catReq.send();
	}

	this.endResults = function() {
				var event = new CustomEvent("end-results");
				var pathParams = ["game", this.gameid, "end_results"];
				var queryParams = "";
				var action = "GET";
				var resultReq = this.xhrSendReq(pathParams, queryParams, action);
				resultReq.onload = function(e) {
					event.data = resultReq.response;
					window.dispatchEvent(event);
				};
				resultReq.send();
			}

		this.statistics = function() {
				var event = new CustomEvent("pulled-stats");
				var pathParams = ["stats"];
				var queryParams = "";
				var action = "GET";
				var statReq = this.xhrSendReq(pathParams, queryParams, action);
				statReq.onload = function(e) {
					event.data = statReq.response;
					window.dispatchEvent(event);
				};
				statReq.send();
		}

		this.saveStatistics = function() {
				var event = new CustomEvent("saved-stats");
				var pathParams = ["stats", this.gameid, "save"];
				var queryParams = "";
				var action = "POST";
				var saveReq = this.xhrSendReq(pathParams, queryParams, action);
				saveReq.onload = function(e) {
					event.data = saveReq.response;
					window.dispatchEvent(event);
				};
				saveReq.send();
		}

			this.xhrSendReq = function(path, query, action) {
				var arrLen = path.length;
				var paramsToUrl = "";
				var action = action.toUpperCase();
				for(i = 0; i < arrLen; i++) {
					if(i == 0) {
					 paramsToUrl += path[i];
					} else {
					 paramsToUrl += "/" + path[i];
					}		
				}
				paramsToUrl += query;

				var xhr = this.createCORSRequest(action, "http://localhost:7777/toptrumps/" + paramsToUrl); // Request type and URL

				if (!xhr) {
  					alert("CORS not supported");
				}

				return xhr;
			}

			this.createCORSRequest = function(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);
    				xhr.responseType = 'json';

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}


			 this.storageAvailable = function(type) {
			    try {
			        var storage = window[type],
			            x = '__storage_test__';
			        storage.setItem(x, x);
			        storage.removeItem(x);
			        return true;
			    } catch(e) {
			        return e instanceof DOMException && (
			            // everything except Firefox
			            e.code === 22 ||
			            // Firefox
			            e.code === 1014 ||
			            // test name field too, because code might not be present
			            // everything except Firefox
			            e.name === 'QuotaExceededError' ||
			            // Firefox
			            e.name === 'NS_ERROR_DOM_QUOTA_REACHED') &&
			            // acknowledge QuotaExceededError only if there's something already stored
			            storage.length !== 0;
			    }
			}
			this.init();
}