QUnit.config.reorder = false;

/*
 *  test for submit game
 */

var url = "http://smg-server.appspot.com/games"; 
var gameId;

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
	    gameId = data.gameId;
	    ok(data.gameId);  // the response should be like {gameId : ...} 
	    start();
	});
	
});

// test for failed game submission due to duplicate name
test("Failed Game Submission - Duplicate Game Name",function(){
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
	    equal(data.error, "GAME_EXISTS");
	    start();
	});
	
});

//test for failed game submission due to missing field
test("Failed Game Submission - Missing Field",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "59fbb89b9c6dabc31e3eab86802d817c",
			    gameName : "Test Game", 
			    description : "A test game",
			}),
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "MISSING_INFO");
	    start();
	});
	
});

//test for failed game submission due to wrong access signature
test("Failed Game Submission - Wrong Access Signature",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "wrong signature",
			    gameName : "Test Game", 
			    description : "A test game",
			    url : "http://testgame.appspot.com/",
			}),
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "WRONG_ACCESS_SIGNATURE");
	    start();
	});
	
});


/*
 *  test for update game
 */


// test for failed game update due to duplicate game name
test("Failed Game Update - Duplicate Game Name",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "59fbb89b9c6dabc31e3eab86802d817c",
			    gameName : "Lines Of Action", 
			}),
			type: "PUT",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "GAME_EXISTS");
	    start();
	});
});

//test for failed game update due to wrong access signature
test("Failed Game Update - Wrong Access Signature",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "wrong signature",
			    gameName : "Updated Game", 
			}),
			type: "PUT",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "WRONG_ACCESS_SIGNATURE");
	    start();
	});
	
});

//test for failed game update due to wrong developer Id
test("Failed Game Update - Wrong Developer Id",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "4713482599530496",
			    accessSignature : "4e3ca0fa37ba14a8fdb7de19ad24372f",
			    gameName : "Updated Game", 
			}),
			type: "PUT",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "WRONG_DEVELOPER_ID");
	    start();
	});
});

//test for successful game update
test("Successful Game Update",function(){
	stop();
	var setting = {
			data: JSON.stringify({
			    developerId : "5727644637200384",
			    accessSignature : "59fbb89b9c6dabc31e3eab86802d817c",
			    gameName : "Updated Game", 
			}),
			type: "PUT",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId,setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.success, "UPDATED_GAME");
	    start();
	});
	
});

/*
 *  test for delete game
 */

//test for failed game deletion due to wrong access signature
test("Failed Game Deletion - Wrong Access Signature",function(){
	stop();
	var setting = {
			data: {},
			type: "DELETE",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId+"?developerId=5727644637200384&accessSignature=wrongsignature",setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "WRONG_ACCESS_SIGNATURE");
	    start();
	});
	
});

//test for failed game update due to wrong developer Id
test("Failed Game Deletion - Wrong Developer Id",function(){
	stop();
	var setting = {
			data: {},
			type: "DELETE",
			dataType: "json"
	};

	$.ajax(url+"/"+gameId+"?developerId=4713482599530496&accessSignature=4e3ca0fa37ba14a8fdb7de19ad24372f",setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.error, "WRONG_DEVELOPER_ID");
	    start();
	});
});

//test for successful game update
test("Successful Game Deletion", function(){
	stop();
	var setting = {
			data: {},
			type: "DELETE",
			dataType: "json"
	};
	
	$.ajax(url+"/"+gameId+"?developerId=5727644637200384&accessSignature=59fbb89b9c6dabc31e3eab86802d817c",setting).done(function (data, status, jqXHR){
	    expect(1);
	    equal(data.success, "DELETED_GAME");
	    start();
	});
	
});





