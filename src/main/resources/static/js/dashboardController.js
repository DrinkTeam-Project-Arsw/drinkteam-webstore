
/**
 * @param {username} nickname del usuraio actual 
 * @returns {undefined}
 */
function iniciarLocalStorageUser(username) {

    localStorage.setItem('Actual', username);
    //localStorage.removeItem('key');
    //localStorage.clear();
}


function cerrarLocalStorageUsuario() {
    //localStorage.removeItem('key');
    localStorage.clear();
}




function cerrarSesion() {
    cerrarLocalStorageUsuario();
    location.href = "index.html";
}



/// funciones para cargar datos del front dashboard


function loadDashboard() {
    axios.get('/api/v1/users/' + localStorage.getItem('Actual'))
        .then(function (response) {
            document.getElementById("usernameD").innerHTML = response.data["userNickname"];

            document.getElementById("emailD").innerHTML = response.data["userEmail"];
            document.getElementById("balanceD").innerHTML = response.data["userBalance"];
            document.getElementById("feedbackD").innerHTML = response.data["userFeedback"];

            //llamar otras funciones
            updateProducts();
        })
        .catch(function (error) {
            alert("Error, No se pudo cargar usuario");
        })

}

function updateProducts() {
    var tabla = document.getElementById("tableAllProducts");
    tabla.innerHTML = "<th>#</th><th>Seller</th><th>Product Name</th><th>Description</th><th>Price</th><th></th>" +
        "<tbody id='tbodyTableProducts'></tbody>";

    var tbody = document.getElementById("tbodyTableProducts");

    axios.get('/api/v1/products/')
        .then(function (response) {

            for (var x in response.data) {
                if (response.data[x]["productUser"] != localStorage.getItem('Actual')) {
                    //alert(response.data[x]['productName']);
                    var filatr = document.createElement("tr");

                    var productId = "'" + String(response.data[x]["productId"]) + "'";
                    var numeroFila = parseInt(x) + 1;
                    filatr.innerHTML = '<td>' + numeroFila + '</td>' +

                        '<td>' + response.data[x]["productUser"] + '</td>' +
                        '<td>' + response.data[x]["productName"] + '</td>' +
                        '<td>' + response.data[x]["productDescription"] + '</td>' +
                        '<td>$' + response.data[x]["productPrice"] + ' USD</td>' +
                        '<td> <button onclick="comprarProducto(' + productId +
                        ',' + localStorage.getItem('Actual') +
                        ')" class="btn btn-primary">Buy</button> </td>';
                    tbody.appendChild(filatr);
                }

            }
        })
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
