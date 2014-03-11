/**
 * javascript unit tests for ajax between developer page Version1 and Server
 */

var base = "https://v2sever.appspot.com";  // need to be altered

// test for memory usage request
test("Success Request Average Memory Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/memory";
	var setting = {
			data: {
				userId: "abc@gmail.com",
				gameId: "20",
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(data.success);
		ok(data.memory);  // the response should be like {sucess: true, memory: "blabla"}  
	});
	
});

//test for cpu usage request (should deny, no such game id)
test("Fail Request Average CPU Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/cpu";
	var setting = {
			data: {
				userId: "abc@gmail.com",
				gameId: "120"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"nogame");  // the response should be like {success: false, error: "nogame"} 
	});
	
});