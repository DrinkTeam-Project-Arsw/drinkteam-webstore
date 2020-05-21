var stompClient = null;

function setConnected(connected) {

    if (connected) {
        alertify.success("Connecting to server...");
    }
    else {
        alertify.error("Disconnecting from server...");
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
    consultarNotificaiones();
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

async function sendRequest(message, date, destination, send, url, funcion, viewed) {
    alertify.success("Sending request...");

    console.log("SI ENTRO ----------------------------------------------------------!");
    // enviar el usuario que lo envia, el usuario destino, de que funcion, fecha, url, vista: false,
    var notificacion = {
        'notificationMessage': message,
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
    alertify.success("Receiving request...");

    console.log(noti);


    var pathname = window.location.pathname;
    if (noti.notificationFunction == "newProduct") {
        if (noti.notificationSend == localStorage.Actual) {
            document.getElementById("tableYourAds").innerHTML = "";
            updateAds();
            alertify.success("Success, Registered Product");
        } else {
            alertify.message("<b>" + noti.notificationSend + "</b> " + noti.notificationMessage + "!");
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
                updateAds();
                updateOthersTablesSeller();
            }
            document.getElementById('notifications').innerHTML += '<a class="dropdown-item" href="#"><b>' + noti.notificationSend + '</b> ' + noti.notificationMessage + '!</a>';
            var notificaiones = document.getElementById('alertNotify').innerHTML
            document.getElementById('alertNotify').innerHTML = parseInt(notificaiones) + 1;
            alertify.success("<b>" + noti.notificationSend + "</b>  " + noti.notificationMessage + "!");
        }
    } else if (noti.notificationFunction == "editTransaction") {
        if (noti.notificationDestination == localStorage.Actual) {
            console.log(pathname);
            if (pathname == '/transaction.html') {
                loadTransaction();
            }
            document.getElementById('notifications').innerHTML += '<a class="dropdown-item" href="#"><b>' + noti.notificationSend + '</b> ' + noti.notificationMessage + '!</a>';
            var notificaiones = document.getElementById('alertNotify').innerHTML
            document.getElementById('alertNotify').innerHTML = parseInt(notificaiones) + 1;
            alertify.success("<b>" + noti.notificationSend + "</b>  " + noti.notificationMessage + "!");
        } else if(noti.notificationSend == localStorage.Actual){
            if (pathname == '/transaction.html') {
                loadTransaction();
            }
        }
    } 
    else if (noti.notificationFunction == "newMessage") {
        if (noti.notificationDestination == localStorage.Actual) {
            if (pathname != '/transaction.html') {
                alertify.success("<b>" + noti.notificationSend + "</b> " + noti.notificationMessage + "!");
                document.getElementById('notifications').innerHTML += '<a class="dropdown-item" href="' + noti.notificationUrl + '"><b>' + noti.notificationSend + '</b> ' + noti.notificationMessage + '!</a>';
                var notificaiones = document.getElementById('alertNotify').innerHTML
                document.getElementById('alertNotify').innerHTML = parseInt(notificaiones) + 1;
            }
        }

    }
}

async function consultarNotificaiones() {
    // debe consultar y mostrar a usuario sus notificaciones
    await axios.get('/api/v1/notifications/' + localStorage.getItem('Actual'))
        .then(function (response) {
            console.log("exito consulta notificactiones")
            console.log(response.data)

            var noti = response.data;
            var nNoti = 0;

            for (var x in noti) {
                if (!noti[x]["notificationViewed"]) {
                    console.log(noti[x])
                    var notificationId = "'" + String(noti[x]["notificationId"]) + "'";
                    var url = "'" + String(noti[x]["notificationUrl"]) + "'";
                    document.getElementById('notifications').innerHTML += '<a class="dropdown-item btn btn-info"' +
                        'onclick="goToLink(' + notificationId + ',' + url +')"' +
                        '"><b>' + noti[x]["notificationSend"] + '</b> ' + noti[x]["notificationMessage"] + '! <sup>' 
                        + noti[x]["notificationDate"] + '</sup></a>';
                    nNoti += 1
                }
            }
            document.getElementById('alertNotify').innerHTML = parseInt(nNoti);


        })
}

async function goToLink(id, url){
    console.log(id);
    console.log(url);
    await axios.put('/api/v1/notifications/' + id)
        .then(function (response) {
            console.log(response.data);
            var web = url;
            location.href = web;
        })
        .catch(function (error) {
            alerta = 'Error, damaged notification';
            alertify.error(alerta);
        })
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

