/**
 * javascript unit tests for ajax between developer page Version1 and Server
 */

var base = "https://v2sever.appspot.com";  // need to be altered

// test for successful login into console
test("Success Login into console Test",function(){
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

//test for failed login into console (incorrect password)
test("Fail Password Login into console Test",function(){
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

//test for memory usage request (should deny, no such game id)
test("Fail Request Average Memory Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/memory";
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

//test for memory usage request (should deny, no such user id)
test("Fail Request Average Memory Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/memory";
	var setting = {
			data: {
				userId: "error@gmail.com",
				gameId: "20"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"} 
	});
	
});

// test for cpu usage request
test("Success Request Average CPU Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/cpu";
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
		ok(data.cpu);  // the response should be like {sucess: true, cpu: "blabla"}  
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

//test for cpu usage request (should deny, no such game id)
test("Fail Request Average CPU Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/cpu";
	var setting = {
			data: {
				userId: "error@gmail.com",
				gameId: "20"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"nouser");  // the response should be like {success: false, error: "nogame"} 
	});
	
});

// test for network usage request
test("Success Request Average Network Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/network";
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
		ok(data.network);  // the response should be like {sucess: true, network: "blabla"}  
	});
	
});

//test for network usage request (should deny, no such game id)
test("Fail Request Average Network Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/network";
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

//test for network usage request (should deny, no such game id)
test("Fail Request Average Network Usage",function(){
	stop(1000);  // wait at most 1 second for the completion of the ajax call
	var url = base + "/dev/usage/network";
	var setting = {
			data: {
				userId: "error@gmail.com",
				gameId: "20"
			},
			type: "POST",
			dataType: "json"
	};

	$.ajax(url,setting).done(function (data, status, jqXHR){
		start();
		expect(2);
		ok(!data.success);        
		equal(data.error,"nouser");  // the response should be like {success: false, error: "nogame"} 
	});
	
});

// test for verifyMove playerInfo request
test("Success Request verifyMove playerInfo",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/playerInfo"; //playerInfo: players' info list
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(data.success);
        ok(data.verifyMove.playerInfo);  // the response should be like {sucess: true, playerInfo: "blabla"}
    });

});

//test for verifyMove playerInfo (should deny, no such user id)
test("Fail Request verifyMove playerInfo",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/playerInfo"; //playerInfo: players' info list
    var setting = {
        data: {
            userId: "error@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"}
    });

});

// test for verifyMove state request
test("Success Request verifyMove state",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/state"; //state: game's current state
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(data.success);
        ok(data.verifyMove.state);  // the response should be like {sucess: true, state: "blabla"}
    });

});

//test for verifyMove state (should deny, no such user id)
test("Fail Request verifyMove playerInfo",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/state"; //state: game's current state
    var setting = {
        data: {
            userId: "error@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"}
    });

});

// test for verifyMove lastState request
test("Success Request verifyMove lastState",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastState"; //lastState: game's last state
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(data.success);
        ok(data.verifyMove.lastState);  // the response should be like {sucess: true, lastState: "blabla"}
    });

});

//test for verifyMove lastState (should deny, no such user id)
test("Fail Request verifyMove lastState",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastState"; //lastState: game's last state
    var setting = {
        data: {
            userId: "error@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"}
    });

});

// test for verifyMove lastMove request
test("Success Request verifyMove lastMove",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastMove"; //lastMove: game's last move
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(data.success);
        ok(data.verifyMove.lastMove);  // the response should be like {sucess: true, lastMove: "blabla"}
    });

});

//test for verifyMove lastMove (should deny, no such user id)
test("Fail Request verifyMove lastMove",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastMove"; //lastMove: game's last move
    var setting = {
        data: {
            userId: "error@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"}
    });

});

// test for verifyMove lastMovePlayerId request
test("Success Request verifyMove lastMovePlayerId",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastMovePlayerId"; //lastMovePlayerId: ID of player who did last move
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(data.success);
        ok(data.verifyMove.lastMovePlayerId);  // the response should be like {sucess: true, lastMovePlayerId: "blabla"}
    });

});

//test for verifyMove lastMovePlayerId (should deny, no such user id)
test("Fail Request verifyMove lastMovePlayerId",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/verifyMove/lastMovePlayerId"; //lastMovePlayerId: ID of player who did last move
    var setting = {
        data: {
            userId: "error@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error,"nouser");  // the response should be like {success: false, error: "nouser"}
    });

});

