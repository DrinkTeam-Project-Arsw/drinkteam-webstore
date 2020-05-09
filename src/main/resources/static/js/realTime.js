var stompClient = null;

function setConnected(connected) {
    
    if (connected) {
        alertify.success("Conectado al servidor...");
    }
    else {
        alertify.error("Desconectado del servidor...");
    }
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/syncup', function (response) {
            showMessage(JSON.parse(response.body).content);
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

function sendRequest(tabla) {
    alertify.success("Enviando solicitud...");
    stompClient.send("/webStore/upgrade", {}, JSON.stringify({'userNickname': localStorage.Actual , "tableName": tabla}));
}

function showMessage(message) {
    alertify.success("Recibiendo Solicitud...");
    alertify.success(message);
}

$(function () {
    console.log(">>> Servidor Sincronizado...");
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    //$( "#connect" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendRequest("tabla prueba2 2"); });
});

