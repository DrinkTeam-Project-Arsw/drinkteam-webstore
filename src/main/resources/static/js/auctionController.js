const CLOUDINARY_URL_PREVIEW = 'https://res.cloudinary.com/dja8smkgx/image/upload/v';

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
    //localStorage.removeItem('key');
    //localStorage.clear();
}


function cerrarLocalStorageUsuario() {
    //localStorage.removeItem('key');
    localStorage.clear();
}



async function crearSubasta() {
    var nullAlert = false;
    var alerta;
    if (document.getElementById("initialPrice").value === '') {
        nullAlert = true;
        alerta = ' Enter the initial price of the product.';
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
            await axios.post('/api/v1/auctions/', {
                "1": {
                    auctionInitPrice: document.getElementById("initialPrice").value,
                    auctionDate: 1,
                    auctionDateFinal: 1,
                    auctionTimeToWait: 1,
                    auctionType: 1 ,
                    auctionActive: true,
                    sellerId: localStorage.getItem('Product'),
                    productId:localStorage.getItem('Product'),
                    auctionStatus: "En Curso",
                    productName: document.getElementById("upProductName").value
                }
            })
                .then(function (response) {
                    console.log(response.data);
                    var text = ["Success", "Registered Auction"];
                    var web = "profile.html";
                    //alertify.success(text[0]);
                    //alert(text[1]);
                    sendRequest("auctionProduct", "");
                    alertify.alert(text[0], text[1]).set('label', 'OK');
                    alert(text[1]);
                    callAlert(text, web);
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
            document.getElementById("upProductName").disabled = true;
            document.getElementById("upDescription").disabled = true;
            document.getElementById("upPrice").disabled = true;

        })
        .catch(function (error) {
            console.log(error);
            alert("Error, No se pudo cargar el producto");
        })

}

const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/dja8smkgx/image/upload';

const CLOUDINARY_PRESETS = 'b059hpk6';

const CLOUDINARY_PRESETS_2 = 'ml_default';

/**
 * Subir imagen principal del producto
 * 
 */
const imagePreview = document.getElementById('imgProfile-preview');
const mainImageUploader = document.getElementById('selectImageProfile');
const mainUploadBar = document.getElementById('main-bar');

mainImageUploader.addEventListener('change', async (e) => {
    var file = e.target.files[0];

    var formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', CLOUDINARY_PRESETS);

    var res = await axios.post(CLOUDINARY_URL, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        onUploadProgress(e) {

            var progress = (e.loaded * 100) / e.total;
            mainUploadBar.setAttribute('aria-valuenow', progress);
            mainUploadBar.style.width = progress + "%";


        }
    });

    mainUploadBar.className = "progress-bar bg-success";
    imagePreview.src = res.data.secure_url;
    document.getElementById('upProfileImage').value = res.data.version + "/" + res.data.public_id + "." + res.data.format;
});
/// Funcion para llamar las alertas de alertify

function callAlert(text, web) {
    if (web !== null) {
        alertify.alert(text[0], text[1]).set('label', 'OK');
        location.href = web;
    } else {
        alertify.alert(text[0], text[1]).set('label', 'OK');
    }

}
