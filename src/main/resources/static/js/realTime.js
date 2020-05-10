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
    await stompClient.send("/webStore/upgrade", {}, JSON.stringify({ 'userNickname': localStorage.Actual, "function": funcion }));
}

function showMessage(message) {
    console.log("Recibiendo Solicitud...");
    alertify.success("Recibiendo Solicitud...");
    //alertify.success(message.userNickname + '-' + message.function);

    console.log('Mensaje guardado ');

    console.log("retrona: " + message.function);
    var pathname = window.location.pathname;
    if (message.function == "newProduct") {
        if (message.userNickname == localStorage.Actual) {
            document.getElementById("tableYourAds").innerHTML = "";
            updateAds();
            alertify.success("Success, Registered Product");
        } else {
            alertify.message("User <b>" + message.userNickname + "</b> has published a new product!");
            if (pathname == '/dashboard.html') {
                document.getElementById("divAllProducts").innerHTML = "";
                agregarProductos({});
            }
        }
    } else if (message.function == "deleteProduct") {
        if (message.userNickname == localStorage.Actual) {
            document.getElementById("tableYourAds").innerHTML = "";
            updateAds();
            alertify.success("Success, Deleted Product");
        } else {
            if (pathname == '/dashboard.html') {
                document.getElementById("divAllProducts").innerHTML = "";
                agregarProductos({});
            }
        }

    } else if (message.function == "newTransaction") {

        if (pathname == '/profile.html') {
            document.getElementById("tableInProcessSeller").innerHTML = "";
            updateOthersTablesSeller();
        }
    } else if (message.function == "newMessage") {

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
    $("#send").click(function () { sendRequest("tabla prueba2 2"); });
});

