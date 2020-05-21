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

async function sendRequest(message, date, destination, send, url, funcion, viewed) {
    alertify.success("Enviando solicitud...");
    
    console.log("SI ENTRO ----------------------------------------------------------!");
    // enviar el usuario que lo envia, el usuario destino, de que funcion, fecha, url, vista: false,
    var notificacion = { 'notificationMessage': message,
        "notificationDate": date, 
        "notificationDestination": destination,
        "notificationSend": send,
        "notificationUrl": url,
        "notificationFunction": funcion,
        "notificationViewed": viewed,
    };
    console.log(notificacion);
    await stompClient.send("/webStore/upgrade", {}, JSON.stringify(notificacion));
}

function showMessage(noti) {
    console.log("Recibiendo Solicitud...");
    alertify.success("Recibiendo Solicitud...");

    console.log(noti);

    
    var pathname = window.location.pathname;
    if (noti.notificationFunction == "newProduct") {
        if (noti.notificationSend == localStorage.Actual) {
            document.getElementById("tableYourAds").innerHTML = "";
            updateAds();
            alertify.success("Success, Registered Product");
        } else {
            alertify.message("<b>" + noti.notificationSend + "</b> "+ noti.notificationMessage +"!");
            if (pathname == '/dashboard.html') {
                document.getElementById("divAllProducts").innerHTML = "";
                agregarProductos({});
            }
        }
    } else if (noti.notificationFunction == "editProduct") {
        if (pathname == '/dashboard.html') {
            document.getElementById("divAllProducts").innerHTML = "";
            agregarProductos({});
        }
    } else if (noti.notificationFunction == "auctionProduct") {
        if (pathname == '/dashboard.html') {
            document.getElementById("divAllProducts").innerHTML = "";
            agregarProductos({});
        }
    } else if (noti.notificationFunction == "deleteProduct") {
        if (noti.notificationSend == localStorage.Actual) {
            document.getElementById("tableYourAds").innerHTML = "";
            updateAds();
            alertify.success("Success, Deleted Product");
        } else {
            if (pathname == '/dashboard.html') {
                document.getElementById("divAllProducts").innerHTML = "";
                agregarProductos({});
            }
        }

    } else if (noti.notificationFunction == "newTransaction") {
        if (noti.notificationDestination == localStorage.Actual) {

            if (pathname == '/profile.html') {
                document.getElementById("tableInProcessSeller").innerHTML = "";
                document.getElementById("tableHistorySeller").innerHTML = "";
                updateOthersTablesSeller();
            }
            document.getElementById('notifications').innerHTML += '<a class="dropdown-item" href="#"><b>' + noti.notificationSend + '</b> '+ noti.notificationMessage +'!</a>';
            var notificaiones = document.getElementById('alertNotify').innerHTML
            document.getElementById('alertNotify').innerHTML = parseInt(notificaiones) + 1;
            alertify.success("<b>" + noti.notificationSend + "</b> wants to buy a product!");
        }
    } else if (noti.notificationFunction == "newMessage") {
        if (noti.notificationDestination == localStorage.Actual) {
            if (pathname != '/transaction.html') {
                alertify.success("<b>" + noti.notificationSend + "</b> "+ noti.notificationMessage +"!");
                document.getElementById('notifications').innerHTML += '<a class="dropdown-item" href="'+ noti.notificationUrl +'"><b>' + noti.notificationSend + '</b> '+ noti.notificationMessage +'!</a>';
                var notificaiones = document.getElementById('alertNotify').innerHTML
                document.getElementById('alertNotify').innerHTML = parseInt(notificaiones) + 1;
            }
        }

    }
}

function consultarNotificaiones(){
    // debe consultar y mostrar a usuario sus notificaciones
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

