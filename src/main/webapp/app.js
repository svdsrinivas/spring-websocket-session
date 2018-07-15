var ws = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
  var stokenccp = $("#stoken").val()+"ccp";
   ws = new WebSocket('ws://localhost:8080/spring-websocket-plain/portfolio/'+stokenccp);
   ws.onmessage = function(data){
        	console.log(data);
            showGreeting(data.data);
   }
   setConnected(true);
}

function disconnect() {
    if (ws !== null) {
    	ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	var stokenccp = $("#stoken").val()+"cm";
	var data = JSON.stringify({'offerId': $("#name").val(),'stoken':stokenccp})
    ws.send(data);
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});