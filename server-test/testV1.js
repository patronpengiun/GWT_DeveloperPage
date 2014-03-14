/**
 * javascript unit tests for ajax between developer page Version1 and Server
 * As a developer, I should be able to create an account, and then submit
 * my new game in the console. Upon submission, I will need to provide the
 * Game URL, width and height for the iframe that will load the URL, number
 * of players between X and Y (for the matching service), is it turn based, 
 * default time per turn, whether has AI (artificial intelligence), where 
 * has pass-and-play. Images description of the game(printscreens of the 
 * game, of fixed size, for game page representation), and a brief game 
 * description.

Entities:
/games
Game: gameId, playerIdOfGameDeveloper, url, name, pics, isAuthorized, 
parameters (width, height, minPlayers, maxPlayers, …)


*/


var base = "https://v1server.appspot.com";  // need to be altered

// 1. test for successful sign in
test("Success Sign In Test", function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "dev/signin"
	var setting = {
		data: {
				userId: "abc@gmail.com",
				password: "123456"
		},
		type: "POST",
		dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});

});

// 2. test for failed sign in (email already used)
test("Fail Password Login Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/signin"
	var setting = {
			data: {
				userId: "abc@gmail.com",
				password: "345678"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"used_email");  // the response should be like {success: false, error: "used_email"} 
	});


// 3. test for successful login 
test("Success Login Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/login"
	var setting = {
			data: {
				userId: "abc@gmail.com",
				password: "123456"
			},
			type: "GET",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 4. test for failed login (incorrect password)
test("Fail Password Login Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/login"
	var setting = {
			data: {
				userId: "abc@gmail.com",
				password: "345678"
			},
			type: "GET",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"pwd");  // the response should be like {success: false, error: "pwd"} 
	});
});	

// 5. test for successful game URL submission
test("Success Game URL Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/url"
	var setting = {
			data: {
				url: "chess.platform.com"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 6. test for failed game URL submission
test("Fail Game URL Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/url"
	var setting = {
			data: {
				url: "chess.platform.com"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"illegal_url");  // the response should be like {success: false, error: "illegal_url"} 
	});
});	


// 7. test for successful iframe dimension submission
test("Success iframe Dimension Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/iframe_dimension"
	var setting = {
			data: {
				width: "500",
				height: "700"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});
// 8. test for failed ifram dimension submission
test("Fail iframe Dimension Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/iframe_dimension"
	var setting = {
			data: {
				width: "500",
				height: "700"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"illegal_iframe_dimension");  // the response should be like {success: false, error: "illegal_iframe_dimension"} 
	});
});	


// 9. test for successful X and Y submission
test("Success X and Y Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/player_nums"
	var setting = {
			data: {
				X: "2",
				Y: "5"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 10.test for failerd X and Y submission
test("Fail X and Y Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/player_nums"
	var setting = {
			data: {
				X: "2",
				Y: "5"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"illegal_player_nums");  // the response should be like {success: false, error: "illegal_player_nums"} 
	});
});	


// 11.test for successful if-turn-based submission
test("Success if-turn-based Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/turnbased"
	var setting = {
			data: {
				if_turn_based: "true"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 12.test for failed if-turn-based submission
test("Fail if-turn-based Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/turnbased"
	var setting = {
			data: {
				if_turn_based: "1"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"true_false_expected");  // the response should be like {success: false, error: "true_false_expected"} 
	});
});	


// 13.test for successful if-has-AI submission
test("Success if-has-AI Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/has_ai"
	var setting = {
			data: {
				if_has_AI: "true"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 14.test for failed if-has-AI submission
test("Fail if-has-AI Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/has_ai"
	var setting = {
			data: {
				if_has_AI: "1"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"true_false_expected");  // the response should be like {success: false, error: "true_false_expected"} 
	});
});	


// 15.test for successful if-pass-and-play submission
test("Success if-pass-and-play Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/if_pass_and_play"
	var setting = {
			data: {
				if_pass_and_play: "true"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 16.test for failed if-pass-and-play submission
test("Fail if-pass-and-play Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/if_pass_and_play"
	var setting = {
			data: {
				if_pass_and_play: "1"				
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"true_false_expected");  // the response should be like {success: false, error: "true_false_expected"} 
	});
});	


// 17.test for successful game screenshots submission
test("Success Game Screenshots Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/screenshots"
	var setting = {
			data: {
				screenshot1: "img1_url",
				screenshot2: "img2_url",
				screenshot3: "img3_url",
				screenshot4: "img4_url",
				screenshot5: "img5_url"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 18.test for failed game screenshots submission
test("Fail Game Screenshots Submission Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/screenshots"
	var setting = {
			data: {
				screenshot1: "img1_url",
				screenshot2: "img2_url",
				screenshot3: "img3_url",
				screenshot4: "img4_url",
				screenshot5: "www.yahoo.com"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"illegal_screenshot_url");  // the response should be like {success: false, error: "illegal_screenshot_url"} 
	});
});	


// 19.test for successful game description submission
test("Success Game Description Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/description"
	var setting = {
			data: {
				description: "Lead your clan to victory! Clash of Clans is an epic combat strategy game. Build your village, train your troops and battle with thousands of other players online!"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true}  
	});
	
});

// 20.test for failed game description submission
test("Fail Game Description Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/description"
	var setting = {
			data: {
				description: "blah(excessive SEO behavior)"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"illegal_description");  // the response should be like {success: false, error: "illegal_description"} 
	});
});	



		
		



