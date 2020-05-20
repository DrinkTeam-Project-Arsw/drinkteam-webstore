amount.oninput = function () {

    if (0 < amount.value.length && amount.value.length < 4) {
        amountView.innerHTML = amount.value;
    } else if (amount.value.length == 4) {
        amountView.innerHTML = amount.value.substring(0, 1) + "." + amount.value.substring(1, amount.value.length)
    } else if (amount.value.length == 5) {
        amountView.innerHTML = amount.value.substring(0, 2) + "." + amount.value.substring(2, amount.value.length)
    } else if (amount.value.length == 6) {
        amountView.innerHTML = amount.value.substring(0, 3) + "." + amount.value.substring(3, amount.value.length)
    } else {
        amountView.innerHTML = "ERROR";
    }

};

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}

async function cargarSaldo() {

    var american = /^(?:3[47][0-9]{13})$/;
    var visa = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
    var master = /^(?:5[1-5][0-9]{14})$/;
    var discover = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/;

    var cardNumber = document.getElementById("upCardNumber");

    var verificado = false;

    if (cardNumber.value.match(american)) {
        //starting with 34 or 37, length 15 digits.
        alertify.success("American");
        verificado = true;
    }
    else if (cardNumber.value.match(visa)) {
        //starting with 4, length 13 or 16 digits.
        alertify.success("Visa");
        verificado = true;
    }
    else if (cardNumber.value.match(master)) {
        //starting with 51 through 55, length 16 digits.
        alertify.success("MAstercard");
        verificado = true;
    }
    else if (cardNumber.value.match(discover)) {
        //starting with 6011, length 16 digits or starting with 5, length 15 digits.
        alertify.success("Discover");
        verificado = true;
    }
    else {
        alertify.error("<b>It is not a valid credit card number!</b>");
        alertify.error("<b>Remember that we only accept Visa, MasterCard, Discover and American Express</b>");
        verificado = false;

    }
    if (verificado === true) {
        var date = document.getElementById("cardExpiry").value;
        date = date.split("/");
        nowDate = new Date();
        if (date[1] == nowDate.getFullYear() % 100) {
            if (date[0] >= nowDate.getMonth()) {
                verificado = true;
            }
            else {
                verificado = false;
            }
        }
        else if (date[1] > nowDate.getFullYear() % 100) {
            verificado = true;
        }
        else {
            verificado = false;
        }
    }

    /*if(verificado=== true){
        alertify.success("Todo en orden");
        var usuario = "";
        await axios.get('/api/v1/users/' + localStorage.getItem('Actual'))
                .then(function (response) {
                    console.log(response.data)
                    usuario = {1:response.data};
                    usuario["1"]['userBalance']+=50;
                    verificado = true

                })
                .catch(function (error) {
                    var alerta = ' Username No encontrado.';
                    alertify.error(alerta);
                    verificado = false

                })
    }*/

    if (verificado === true) {
        if (document.getElementById('amountView').textContent != "ERROR" && document.getElementById('amountView').textContent != '') {
            await axios.put('/api/v1/users/' + localStorage.getItem('Actual') + '/' + document.getElementById('amount').value)
                .then(function (response) {
                    console.log(response.data);
                    var text = 'Successful Deposit!';
                    alertify.success(text);
                    document.getElementById("closeModal").click();
                    var pathname = window.location.pathname;

                    loadProfile();

                })
                .catch(function (error) {
                    var alerta = 'Error depositing, please call your bank.';
                    console.log(error);
                    alertify.error(alerta);
                    verificado = false

                })
        } else {
            alertify.error("<b>Error, invalid dollar amount</b>");
        }

    }
    else {
        alertify.error("<b>Error, Verify card details</b>");
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
