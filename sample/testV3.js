/**
 * javascript unit tests for ajax between developer page Version1 and Server
 */

var base = "https://v3sever.appspot.com";  // need to be altered

// test for successful database query
test("Success Query Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/query"
	var setting = {
			data: {
				userId: "abc@gmail.com",
				sql: "SELECT * FROM DB_SHARED_STATICS WHERE GAMEID = '60'"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(1);
		ok(data.success);  // the response should be like {success: true, ... , ... }  
	});
	
});

//test for failed database query
test("Fail Query Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/query"
	var setting = {
			data: {
				userId: "abc@gmail.com",
				sql: "SELECT * FROM DB_PRIVATE_STATICS WHERE GAMEID = '60'" // denied because the game does not belong to the developer
			},
			type: "GET",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"denied");  // the response should be like {success: false, error: "denied"} 
	});
	
});
		
		




