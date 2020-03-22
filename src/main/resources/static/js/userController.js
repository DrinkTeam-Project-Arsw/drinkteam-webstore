
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


function iniciarSesion() {
    var nullAlert = false;
    var alerta;
    if (document.getElementById("inUsername").value === '') {
        nullAlert = true;
        alerta = ' Enter your username.';
        alertify.error(alerta);
    }
    if (document.getElementById("inPassword").value === '') {
        nullAlert = true;
        alerta = ' Enter your password.';
        alertify.error(alerta);
    }
    if (!nullAlert) {

        axios.get('/webstoreUser/users/' + document.getElementById("inUsername").value)
                .then(function (response) {
                    
                    if (response.data["userPassword"] === document.getElementById("inPassword").value) {
                        iniciarLocalStorageUser(document.getElementById("inUsername").value);
                        location.href = "profile.html";

                    } else {
                        alerta = ' Incorrect username or password.';
                        alertify.error(alerta);
                    }

                })
                .catch(function (error) {
                    alerta = ' Incorrect username or password.';
                    alertify.error(alerta);

                })
    }

}


function registrarUsuario() {
    var nullAlert = false;
    //document.getElementById("alertDiv").innerHTML = "";
    var alerta;
    if (document.getElementById("upUsername").value === '') {
        nullAlert = true;
        
        alerta = ' Enter your username.';
        alertify.error(alerta);
        //document.getElementById("alertDiv").innerHTML += divAlerta(alerta);
    }
    if (document.getElementById("upEmail").value === '') {
        nullAlert = true;
        alerta = ' Enter your Email.';
        alertify.error(alerta);

    }
    if (document.getElementById("upPassword").value === '') {
        nullAlert = true;
        alerta = ' Enter your Password.';
        alertify.error(alerta);
    }

    if (document.getElementById("upPassword2").value === '') {
        nullAlert = true;
        alerta = ' Please, Repeat the Password.';
        alertify.error(alerta);

    }
    if (document.getElementById("upPassword2").value !== document.getElementById("upPassword").value) {
        nullAlert = true;
        alerta = ' The Passwords not similars.';
        alertify.error(alerta);

    }

    if (!nullAlert) {
        axios.post('/webstoreUser/users/', {
            "1": {
                userEmail: document.getElementById("upEmail").value,
                userPassword: document.getElementById("upPassword").value,
                userNickname: document.getElementById("upUsername").value 
            }
            

        })
                .then(function (response) {
                    console.log(response.data);
                    var text = ["Success","Registered User"];
                    var web = "login.html";
                    //alertify.success(text[0]);
                    //alert(text[1]);
                    alertify.alert(text[0],text[1]).set('label', 'OK');
                    alert(text[1]);
                    callAlert(text, web);
                    
                })
    } else {
        alertify.error("<b>>>Please, fill in all the required fields.<<</b>");
    }

}

function cerrarSesion() {
    cerrarLocalStorageUsuario();
    location.href = "index.html";
}

function loadProfile() {
    axios.get('/webstoreUser/users/' + localStorage.getItem('Actual'))
            .then(function (response) {
                document.getElementById("usernameP").innerHTML = response.data["userNickName"];
                
                document.getElementById("emailP").innerHTML = response.data["userEmail"];
                document.getElementById("balanceP").innerHTML = response.data["userBalance"];
                document.getElementById("feedbackP").innerHTML = response.data["userFeedback"];

            })
            .catch(function (error) {
                alert("Error, No se pudo cargar usuario");
            })

}

function callAlert(text, web){
    if(web!==null){
        alertify.alert(text[0],text[1]).set('label', 'OK');
        location.href = web;
    } else {
        alertify.alert(text[0],text[1]).set('label', 'OK');
    }
    
}
