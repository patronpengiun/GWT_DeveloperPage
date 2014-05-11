// sleep function to ensure test sequence, as the ajax calls are async
function sleep(duration){
    var start = new Date().getTime();
    while(true) {
	if ((new Date().getTime() - start) > duration){
	    break;
    }
  }
}

var url = "http://smg-server.appspot.com/games"; 

// test for successful game submission
test("Successful Game Submission",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "59fbb89b9c6dabc31e3eab86802d817c",
			    gameName : "Lines of Action", 
			    description : "A simple board game",
			    url : "http://linesofactiongame.appspot.com/",
			}),
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
	    expect(1);
	    ok(data.gameId);  // the response should be like {gameId : ...}  
	    start();
	});
	
});

// test for failed game submission due to duplicate name
test("Failed Game Submission - Duplicate Game Name",function(){
        sleep(2000);
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "59fbb89b9c6dabc31e3eab86802d817c",
			    gameName : "Lines of Action", 
			    description : "A simple board game",
			    url : "http://linesofactiongame.appspot.com/",
			}),
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.gameId);  // the response should be like {gameId : ...}  
	    start(data.error, "GAME_EXISTS");
	});
	
});


