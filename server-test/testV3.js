/**
 * javascript unit tests for ajax between developer page Version3 and Server
 */


var base = "https://v3sever.appspot.com";  // need to be altered

// test for game-played frequency request
test("Success Request Frequency Game Played",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/game-frequency"; //frequency: average times game being played every day
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
        ok(data.frequency);  // the response should be like {sucess: true, frequency: "blabla"}
    });

});

//test for game-played frequency request (should deny, no such game id)
test("Fail Request Frequency Game Played",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/game-frequency"; //frequency: average times game being played every day
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


//test for player percentage request
test("Success Request Player Percentage",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/percentage";
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
        ok(data.percentage);  // the response should be like {sucess: true, percentage: "blabla"}
    });

});


//test for player percentage request(should deny, no such userID)
test("Fail Request Player Percentage",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/percentage";
    var setting = {
        data: {
            userId: "abc123@gmail.com",
            gameId: "20"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2);
        ok(!data.success);
        equal(data.error, "User not exits");  // the response should be like {success: false, error: "User not exits"}
    });

});



//test for game feedback request
test("Success Request Feedback",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/feedback";
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
        expect(2); //maybe more than 2
        ok(data.success);
        ok(data.feedback);  // the response should be like {sucess: true, feedback: "blabla"}
    });

});


//test for game feedback request ( should deny, request other developer's game information )
test("Fail Request Feedback",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/feedback";
    var setting = {
        data: {
            userId: "abc@gmail.com",
            gameId: "40"
        },
        type: "POST",
        dataType: "json"
    };

    $.ajax(url,setting).done(function (data, status, jqXHR){
        start();
        expect(2); //maybe more than 2
        ok(!data.success);
        equal(data.error, "no permission");  // the response should be like {success: false, error: "no permission"}
    });

});

//test for game feature frequency: historgram of ranking
test("Success Request Ranking-Histogram Frequency",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/feature-frequency/ranking-historgram";
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
        ok(data.frequency);  // the response should be like {sucess: true, frequency: "blabla"}
    });

});

//test for game feature frequency: tokens transfers
test("Success Request Tokens-Transfers Frequency",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/feature-frequency/tokens-transfers";
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
        ok(data.frequency);  // the response should be like {sucess: true, frequency: "blabla"}
    });

});

//test for game feature frequency: viewing
test("Success Request viewing players' number",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/feature-frequency/viewing";
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
        ok(data.frequency);  // the response should be like {sucess: true, frequency: "blabla"}
    });

});

//test for game feature frequency: back&forward
test("Success Request viewing players' number",function(){
    stop(1000);  // wait at most 1 second for the completion of the ajax call
    var url = base + "/dev/popularity/feature-frequency/back_forward";
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
        ok(data.frequency);  // the response should be like {sucess: true, frequency: "blabla"}
    });

});

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





