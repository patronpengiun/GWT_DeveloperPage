/**
 * javascript unit tests for ajax between developer page Version1 and Server
 */

var base = "https://v1sever.appspot.com";  // need to be altered

// test for successful login 
test("Success Login Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/login"
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

var base = "https://v1sever.appspot.com";  // need to be altered

//test for failed login (incorrect password)
test("Fail Password Login Test",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/login"
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
		
		




