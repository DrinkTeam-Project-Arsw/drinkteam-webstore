

$(".custom-file-input").on("change", function () {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});


/**
 * @param {username} nickname del usuraio actual 
 * @returns {undefined}
 */
function iniciarLocalStorageUser(username) {
    localStorage.setItem('Actual', username);
}


function cerrarLocalStorageUsuario() {
    localStorage.clear();
}

async function actualizarProducto() {
    var nullAlert = false;
    var alerta;
    if (document.getElementById("upProductName").value === '') {
        nullAlert = true;
        alerta = ' Enter product name.';
        alertify.error(alerta);
    }
    if (document.getElementById("upDescription").value === '') {
        nullAlert = true;
        alerta = ' Enter the product description.';
        alertify.error(alerta);

    }
    if (document.getElementById("upPrice").value === '') {
        nullAlert = true;
        alerta = ' Enter the price of the product.';
        alertify.error(alerta);
    }

    if (document.getElementById("upPassword").value === '') {
        nullAlert = true;
        alerta = ' Please, Enter your Password.';
        alertify.error(alerta);

    }
    if (document.getElementById("upPassword2").value !== document.getElementById("upPassword").value) {
        nullAlert = true;
        alerta = ' The Passwords not similars.';
        alertify.error(alerta);

    }    

    if (!nullAlert) {
        if(cpd===document.getElementById("upPassword").value){
            await axios.put('/api/v1/products/' + localStorage.getItem('Actual') + '/' + localStorage.getItem('Product'), {
                "1": {
                    productName: document.getElementById("upProductName").value,
                    productDescription: document.getElementById("upDescription").value,
                    productPrice: document.getElementById("upPrice").value,
                    productImage: image
                }
    
    
            })
                .then(function (response) {
                    console.log(response.data);
                    var text = "Success, Update Info";
                    alertify.success(text);
                    document.getElementById("upPassword").value = ""
                    document.getElementById("upPassword2").value = ""
                    
                    setTimeout(window.location.href = "profile.html",4000);
                    
                    //callAlert(text, web);
    
                })
        }else{
            alertify.error("<b>Incorrect Password </b>");
        }
        
    } else {
        alertify.error("<b>Something happened, check the data you want to change</b>");
    }
}

function cerrarSesion() {
    cerrarLocalStorageUsuario();
    location.href = "index.html";
}

var cpd = '';
var image = '';

function loadData() {
    axios.get('/api/v1/users/' + localStorage.getItem('Actual'))
        .then(function (response) {
            cpd = response.data["userPassword"];
        })
        .catch(function (error) {
            console.log(error);
            alert("Error, No se pudo cargar el usuario");
        })
    axios.get('/api/v1/products/' + localStorage.getItem('Actual') +'/' + localStorage.getItem('Product'))
        .then(function (response) {
            console.log(response.data);
            document.getElementById("productNameU").innerHTML = response.data["productName"];
            document.getElementById("upId").value = response.data["productId"];
            document.getElementById("upProductName").value = response.data["productName"];
            document.getElementById("upDescription").value = response.data["productDescription"];
            document.getElementById("upPrice").value = response.data["productPrice"];
            image = response.data["productImage"];

            //disabled inputs
            document.getElementById("upId").disabled = true;

        })
        .catch(function (error) {
            console.log(error);
            alert("Error, No se pudo cargar el producto");
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