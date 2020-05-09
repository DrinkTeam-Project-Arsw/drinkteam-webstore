var stompClient = null;

function setConnected(connected) {
    //$("#connect").prop("disabled", connected);
    //$("#disconnect").prop("disabled", !connected);
    if (connected) {
        console.log(">>> Esta conectado");
        alertify.success("Conectado...");
        //$("#conversation").show();
    }
    else {
        console.log(">>> Esta desconectado");
        alertify.error("Desconectado ....");
        //$("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/synchronization', function (synUp) {
            showMessage(JSON.parse(synUp.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendRequest(table,funcion) {
    console.log(">>> Enviando solicitud cambio...");
    alertify.success("Enviando solicitud cambio......");
    stompClient.send("/app/update", {}, JSON.stringify({'userNickname': localStorage.Actual,"tableName": table,"function":funcion}));
}

function showMessage(message) {
    console.log(">>> Mostrando mensaje...");
    alertify.success("Recibindo respuesta......");
    console.log(">>>"+ message);
    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    console.log(">>> Funcion principal ejecutando...");
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    //$( "#connect" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
    //$( "#send" ).click(function() { sendName(); });
});

