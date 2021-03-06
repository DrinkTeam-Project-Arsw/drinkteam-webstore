const CLOUDINARY_URL_PREVIEW = 'https://res.cloudinary.com/dja8smkgx/image/upload/v';

var contraTransaction = "";
var saldoBuyer= "";
var precioTransaccion = "";

var txnId = getParameterById('txnId');
var stompClient = null;

/**
 * @param String name
 * @return String
 */
function getParameterById(transactionId) {
    transactionId = transactionId.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + transactionId + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

console.log("Transaction: " + txnId)

"use strict"
jQuery(document).ready(function () {

    var navListItems = $('ul.setup-panel li a'),
        allWells = $('.setup-content');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this).closest('li');

        if (!$item.hasClass('disabled')) {
            navListItems.closest('li').removeClass('active');
            $item.addClass('active');
            allWells.hide();
            $target.show();
        }
    });

    $('ul.setup-panel li.active a').trigger('click');

    // DEMO ONLY //
    $('#activate-step-2').on('click', function (e) {
        $('ul.setup-panel li:eq(1)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-2"]').trigger('click');
        $(this).remove();
    })

    $('#activate-step-3').on('click', function (e) {
        $('ul.setup-panel li:eq(2)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-3"]').trigger('click');
        $(this).remove();
    })

});



function cerrarLocalStorageUsuario() {
    //localStorage.removeItem('key');
    localStorage.clear();
}




function cerrarSesion() {
    cerrarLocalStorageUsuario();
    location.href = "index.html";
}



/// funciones para cargar datos del front dashboard

async function loadProfile() {
    await axios.get('/api/v1/users/' + localStorage.getItem('Actual'))
        .then(function (response) {

            document.getElementById("usernameP").innerHTML = "Hi, " + response.data["userNickname"];

            document.getElementById("emailP").innerHTML = response.data["userEmail"];
            document.getElementById("balanceP").innerHTML = response.data["userBalance"];


            document.getElementById("nameP").innerHTML = response.data["userName"];
            document.getElementById("lastnameP").innerHTML = response.data["userLastName"];
            document.getElementById("phoneP").innerHTML = response.data["codeCountry"] + " " + response.data["userPhone"];
            document.getElementById("nProductsP").innerHTML = response.data["products"].length;

            if (response.data["userImage"] === "") {
                document.getElementById("userImageP").src = "./img/noImageUser.jpg";
            }
            else {
                document.getElementById("userImageP").src = CLOUDINARY_URL_PREVIEW + response.data["userImage"];
            }

            //Feedback
            var ul = document.getElementById("feedbackView");
            var feedback = response.data["userFeedback"];
            for (var i = 0; i < feedback; i++) {
                var li = document.createElement("li");
                var star = '<a href="#"><img src="./img/star.png" style="width: 100%; top: -7px; position: relative; filter: drop-shadow(2px 2px 6px #444);"></i></a>';
                li.innerHTML = star;
                ul.appendChild(li);
            }

            //llamar otras funciones
            loadTransaction()
        })
        .catch(function (error) {
            alert("Error, No se pudo cargar usuario");
        })

}

async function loadTransaction() {
    if (txnId != '') {
        //verificar que usuarios hagan parte de la transaccion (comprador y vendedor)

        //consultar transacction
        var response = await axios.get('/api/v1/transactions/' + txnId)

        console.log(response.data);

        //>>> response.data 0 = transaction, 1 = comprador, 2 = vendedor, 3 = producto
        var product = response.data[3];
        var buyer = response.data[1];
        var seller = response.data[2];
        var transaction = response.data[0];
        //Colocar datos

        // transaction
        document.getElementById("transacctionStatus").innerHTML = transaction['transactionState'];
        //// Guardar Imagen de vendedor y comprador
        if (localStorage.Actual == seller['userNickname']) {
            localStorage.avatarActual = seller["userImage"];
            contraTransaction = buyer["userNickname"];
        } else {
            localStorage.avatarActual = buyer["userImage"];
            contraTransaction = seller["userNickname"];
        }

        /// progreso
        var progress = null;
        if (transaction['transactionState'] == "verifying") {
            progress = 20;
        } else if (transaction['transactionState'] == "paying") {
            progress = 40;
        } else if (transaction['transactionState'] == "deposited") {
            progress = 60;
        } else if (transaction['transactionState'] == "sending") {
            progress = 80;
        } else if (transaction['transactionState'] == "complete") {
            progress = 100;
            document.getElementById("progresoEstado").classList.add("bg-success");
        } else if (transaction['transactionState'] == "mediation") {
            progress = 100;
            document.getElementById("progresoEstado").classList.add("bg-danger");
        } else {
            progress = 0;
        }



        document.getElementById("progresoEstado").setAttribute('aria-valuenow', progress);
        document.getElementById("progresoEstado").style.width = progress + "%";



        if (buyer["userNickname"] == localStorage.Actual) {
            if (progress > 39) {
                document.getElementById("activate-step-2").style.visibility = 'hidden';
                document.getElementById("activate-step-2").style.display = 'none';
                $('ul.setup-panel li:eq(1)').removeClass('disabled');
                $('ul.setup-panel li a[href="#step-2"]').trigger('click');
            }
            if (progress > 59) {
                document.getElementById("activate-step-3").style.visibility = 'hidden';
                document.getElementById("activate-step-3").style.display = 'none';
                $('ul.setup-panel li:eq(2)').removeClass('disabled');
                $('ul.setup-panel li a[href="#step-3"]').trigger('click');
            }
            if (progress > 79) {
                // document.getElementById("activate-step-4").style.visibility = 'hidden';
                // document.getElementById("activate-step-4").style.display = 'none';
                document.getElementById("titelPaso5").innerHTML = 'Confirm receipt of the product';
            }
            if (progress > 99) {
                document.getElementById("activate-step-4").style.visibility = 'hidden';
                document.getElementById("activate-step-4").style.display = 'none';
                document.getElementById("titelPaso5").innerHTML = 'The transaction is complete !!';
                
            }
            if (progress == 40) {
                document.getElementById("divfund").style.display = "block"
                document.getElementById("divFundPaid").style.display = "none"
            }
            if (progress > 41) {
                document.getElementById("divfund").style.display = "none"
                document.getElementById("divFundPaid").style.display = "block"
            }

            document.getElementById("titlePanel").innerHTML = '<h3>You are the <b>buyer</b>. Please, follow the steps.</h3>'
            document.getElementById("vistaVendedor").innerHTML = '';
            document.getElementById("vistaComprador").style.display = "block";

            /// Transacccion
            precioTransaccion = (parseFloat(product["productPrice"]) * 0.005) + product["productPrice"];
            document.title = "Transaction - Buyer";
            document.getElementById("fundEscrow").innerHTML = (parseFloat(product["productPrice"]) * 0.005) + product["productPrice"];
            document.getElementById("pagado").innerHTML = (parseFloat(product["productPrice"]) * 0.005) + product["productPrice"];
            /// Producto
            document.getElementById("productName").value = product["productName"];
            document.getElementById("productDescription").value = product["productDescription"];
            document.getElementById("productPrice").value = product["productPrice"];
            document.getElementById("totalPrice").value = (parseFloat(product["productPrice"]) * 0.005) + product["productPrice"];

            ///imagenes
            var imagenes = product['productImage'].split(",");
            var ol = '';
            var divCareusel = '';

            for (let img in imagenes) {
                if (img == 0) {
                    ol += '<li data-target="#myCarousel' + product['productId'] + '" data-slide-to="' + img + '" class="active"></li>';
                    divCareusel += '<div class="item active">' +
                        '<img class="card-img-top rounded-0" src="' + CLOUDINARY_URL_PREVIEW + imagenes[img] + '" alt="' + imagenes[img] + '">' +
                        '</div>';
                } else {
                    ol += '<li data-target="#myCarousel' + product['productId'] + '" data-slide-to="' + img + '"></li>';
                    divCareusel += '<div class="item">' +
                        '<img class="card-img-top rounded-0" src="' + CLOUDINARY_URL_PREVIEW + imagenes[img] + '" alt="' + imagenes[img] + '">' +
                        '</div>';
                }

            }
            var carousel = document.getElementById('photosDiv');
            carousel.innerHTML = '<div class="view overlay">' +
                '<div id="myCarousel' + product['productId'] + '" class="carousel slide" data-ride="carousel">' +
                '<ol class="carousel-indicators">' + ol + '</ol>' +
                '<div class="carousel-inner">' + divCareusel + '</div>' +

                '<a class="left carousel-control" href="#myCarousel' + product['productId'] + '" data-slide="prev">' +
                '<span class="glyphicon glyphicon-chevron-left"></span>' +
                '<span class="sr-only">Previous</span>' +
                '</a>' +
                '<a class="right carousel-control" href="#myCarousel' + product['productId'] + '"  data-slide="next">' +
                '<span class="glyphicon glyphicon-chevron-right"></span>' +
                '<span class="sr-only">Next</span>' +
                '</a>' +
                '</div>' +
                '</div>';



            ///Comprador
            saldoBuyer = buyer["balance"];

            document.getElementById("buyerNickname").innerHTML = buyer["userNickname"];

            document.getElementById("buyerName").value = buyer["userName"];
            document.getElementById("buyerLastname").value = buyer["userLastName"];
            document.getElementById("buyerAddress").value = "Cr 19";
            document.getElementById("buyerPhone").value = "+" + buyer["codeCountry"] + " " + buyer["userPhone"];


            ///Vendedor
            document.getElementById("sellerNickname").value = seller["userNickname"];

            ///Feedback
            var div = document.getElementById("sellerReputation");
            var feedback = seller["userFeedback"];
            var images = '';
            for (var i = 0; i < feedback; i++) {
                //var img = document.createElement("");
                images += '<img src="./img/star.png" style="width: 35px; position: relative; top: -8px">';
                //li.innerHTML = star;

            }
            div.innerHTML = images;

            ///Mensajes CHAT
            messages();
        } else if (seller["userNickname"] == localStorage.Actual) {
            var body = document.getElementById("bodyAlert");
            if (progress < 40) {
                body.innerHTML = 'Your buyer is verifying the product data.' +
                    '<br>Once the buyer verifies that everything is fine, we will notify you..'
            } else if (progress == 40) {
                body.innerHTML = 'Your buyer is depositing the warranty payment.' +
                    '<br>Once the buyer makes the deposit, we will notify you...'
            } else if (progress == 60) {
                body.innerHTML = 'The deposit has been successful.' +
                    '<br>You must send the product to the address we provide.'
                    document.getElementById("divEnviar").style.display = "block"
            } else if (progress == 80) {
                body.innerHTML = 'Waiting for the buyer to confirm.' +
                    '<br>Once the buyer verifies that everything is fine, we will notify you..'
            } else if (progress == 100) {
                body.innerHTML = 'Your buyer is verifying the product data.' +
                    '<br>Once the buyer confirms that the product arrived, we will notify you.'
            }


            /// Transacccion
            document.title = "Transaction - Seller";
            document.getElementById("titlePanel").innerHTML = '<h3>You are the <b>seller</b>. Please, follow the steps.</h3>'
            document.getElementById("vistaComprador").innerHTML = '';
            document.getElementById("vistaVendedor").style.display = "block";

            //vendedro
            document.getElementById("sellerNicknameS").innerHTML = seller["userNickname"];

            // Detalles comprador
            document.getElementById("buyerNicknameS").value = buyer["userNickname"];
            ///Feedback
            var div = document.getElementById("buyerReputationS");
            var feedback = buyer["userFeedback"];
            var images = '';
            console.log(feedback)
            for (var i = 0; i < feedback; i++) {
                //var img = document.createElement("");
                images += '<img src="./img/star.png" style="width: 35px; position: relative; top: -8px">';
                //li.innerHTML = star;

            }
            div.innerHTML = images;

            // PRODUCTO 
            document.getElementById("productNameS").value = product["productName"];
            document.getElementById("productDescriptionS").value = product["productDescription"];
            document.getElementById("productPriceS").value = product["productPrice"];
            document.getElementById("totalPriceS").value = (parseFloat(product["productPrice"]) * 0.005) + product["productPrice"];


            ///Mensajes CHAT
            messages();
        } else {
            alert("Invalid User, does not belong to this transaction")
        }






    } else {
        alert("Error, there is no transaction")
    }
}

/// funciones psar de paso
async function changeStatus(paso) {
    console.log(paso);
    var status = null;
    var activo = true;
    if (paso == 2) {
        status = "paying";
    } else if (paso == 3) {
        document.getElementById("closeModalConfirm").disabled = true
        document.getElementById("closeModalConfirm").textContent = "Loading..."
        document.getElementById("activate-step-3").disabled = true
        document.getElementById("activate-step-3").textContent = "Loading..."
        if (saldoBuyer < precioTransaccion) {
            status = "ERROR"
        } else {
            status = "deposited";
        }

    } else if (paso == 4) {
        document.getElementById("botonEnviar").disabled = true
        document.getElementById("botonEnviar").textContent = "Loading..."
        
        status = "sending";
    } else if (paso == 5) {
        document.getElementById("activate-step-4").disabled = true
        document.getElementById("activate-step-4").textContent = "Loading..."
        status = "complete";
        activo = false;
    } else if (paso == 6) {
        status = "mediation";
    }

    if (status != "ERROR") {
        await axios.put('/api/v1/transactions/', {
            "1": {
                transactionId: txnId,
                TransactionState: status,
                transactionActive: activo
            }
        })
            .then(function (response) {
                console.log(response.data)
                // notificar servidor
                var text = "Success, status has changed";



                //############# enviar datos a servidor
                var message = 'a transaction has changed state';
                var date = '';
                var destination = contraTransaction;
                var send = localStorage.Actual;
                var url = "transaction.html?txnId=" + txnId;
                var funcion = 'editTransaction';

                sendRequest(message, date, destination, send, url, funcion, false)
                alertify.success(text);
                document.getElementById("closeModalConfirm").disabled = false
                document.getElementById("closeModalConfirm").textContent = "Cancel"
                document.getElementById("closeModalConfirm").click()
                document.getElementById("activate-step-3").disabled = false
                document.getElementById("activate-step-3").textContent = "Confirm"
                document.getElementById("botonEnviar").style.display ="none"

            })
            .catch(function (error) {
                console.log(error)
            })
    } else {
        var text = "Error, Insufficient Balance";
        alertify.error(text);
        document.getElementById("closeModalConfirm").disabled = false
        document.getElementById("closeModalConfirm").textContent = "Cancel"
        document.getElementById("closeModalConfirm").click()
        document.getElementById("activate-step-3").disabled = false
        document.getElementById("activate-step-3").textContent = "Confirm"
    }

}


/// Funcion para llamar las alertas de alertify

function callAlert(text, web) {
    if (web !== null) {
        alertify.alert(text[0], text[1]).set('label', 'OK');
        location.href = web;
    } else {
        alertify.alert(text[0], text[1]).set('label', 'OK');
    }

}

async function messages() {
    //consultar Mensajes
    var response = await axios.get('/api/v1/messages/' + txnId)
    for (var i = 0; i < response.data.length; i++) {

        var mensaje = response.data[i];
        var control = '';
        if (mensaje.user == localStorage.Actual) {
            control = '<li style="width:100%;">' +
                '<div class="msj-rta macro">' +
                '<div class="text text-r">' +
                '<p>' + mensaje.data + '</p>' +
                '<p><small>' + mensaje.user + '</small></p>' +
                '</div>' +
                '<div class="avatar" style="padding:0px 0px 0px 10px !important"><img class="img-circle" style="width:100%;" src="' + CLOUDINARY_URL_PREVIEW + mensaje.userImage + '" /></div>' +
                '</li>';
        } else {
            control = '<li style="width:100%">' +
                '<div class="msj macro">' +
                '<div class="avatar"><img class="img-circle" style="width:100%;" src="' + CLOUDINARY_URL_PREVIEW + mensaje.userImage + '" /></div>' +
                '<div class="text text-l">' +
                '<p>' + mensaje.data + '</p>' +
                '<p><small>' + mensaje.user + '</small></p>' +
                '</div>' +
                '</div>' +
                '</li>';
        }

        $("#chat").append(control);
    }
}

function registrarMensaje() {
    var nullAlert = false;
    //document.getElementById("alertDiv").innerHTML = "";
    var alerta;

    if (document.getElementById("mensaje").value === '') {
        nullAlert = true;

        alerta = ' Add message.';
        alertify.error(alerta);
    }

    if (!nullAlert) {

        axios.post('/api/v1/messages/', {
            "1": {
                idTransaction: txnId,
                user: localStorage.Actual,
                data: document.getElementById("mensaje").value,
                userImage: localStorage.avatarActual
            }

        })
            .then(function (response) {
                var text = "Success, message";
                var web = "#sectionList";
                alertify.success(text);

                //############# enviar datos a servidor
                var message = 'sent you a message';
                var date = '';
                var destination = contraTransaction;
                var send = localStorage.Actual;
                var url = "transaction.html?txnId=" + txnId;
                var funcion = 'newMessage';

                sendRequest(message, date, destination, send, url, funcion, false)
            })
        var mensaje = new Message(txnId, localStorage.Actual, $("#mensaje").val(), localStorage.avatarActual);
        $("#mensaje").val("");
        stompClient.send("/topic/transactions." + txnId, {}, JSON.stringify(mensaje));

    } else {
        alertify.error("<b>>>Please, fill in all the required fields.<<</b>");
    }
}

function connectAndSubscribe() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/transactions.' + txnId, function (response) {
            var mensaje = JSON.parse(response.body);
            var control = '';
            if (mensaje.user == localStorage.Actual) {
                control = '<li style="width:100%;">' +
                    '<div class="msj-rta macro">' +
                    '<div class="text text-r">' +
                    '<p>' + mensaje.data + '</p>' +
                    '<p><small>' + mensaje.user + '</small></p>' +
                    '</div>' +
                    '<div class="avatar" style="padding:0px 0px 0px 10px !important"><img class="img-circle" style="width:100%;" src="' + CLOUDINARY_URL_PREVIEW + mensaje.userImage + '" /></div>' +
                    '</li>';
            } else {
                control = '<li style="width:100%">' +
                    '<div class="msj macro">' +
                    '<div class="avatar"><img class="img-circle" style="width:100%;" src="' + CLOUDINARY_URL_PREVIEW + mensaje.userImage + '" /></div>' +
                    '<div class="text text-l">' +
                    '<p>' + mensaje.data + '</p>' +
                    '<p><small>' + mensaje.user + '</small></p>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }

            $("#chat").append(control);
        });
    });
}

class Message {
    constructor(idTransaction, user, data, userImage) {
        this.idTransaction = idTransaction;
        this.user = user;
        this.data = data;
        this.userImage = userImage;
    }
}