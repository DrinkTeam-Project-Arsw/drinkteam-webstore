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
            showMessage(JSON.parse(response.body));
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

async function sendRequest(funcion) {
    alertify.success("Enviando solicitud...");
    await stompClient.send("/webStore/upgrade", {}, JSON.stringify({'userNickname': localStorage.Actual , "function": funcion}));
}

function showMessage(message) {
    alertify.success("Recibiendo Solicitud...");
    //alertify.success(message.userNickname + '-' + message.function);
    
    console.log('Mensaje guardado ');

    console.log("retrona: "+ message.function);
    if(message.function == "newProduct"){
        if(message.userNickname == localStorage.Actual ){
            updateAds();
            alertify.success("Tabla anucios Actualizada");
        }else{
            alertify.success("Usuario "+ mensage.userNickname +" ha publicado un nuevo producto");
        }
    }else{
        alertify.error("MALPARIDOS")
    }
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

