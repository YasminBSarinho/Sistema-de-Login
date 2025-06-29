const form = document.getElementById("formCadastro");
const alertSucesso = document.getElementById('alertSucesso');
const btnOk = document.getElementById('btnOk');
const alertErro = document.getElementById('alertErro');
const btnErro = document.getElementById('btnErro');

function cadastrar() {
    const usuario = {
        nome: document.getElementById("nome").value,
        login: document.getElementById("login").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("password").value
    };

    fetch("http://localhost:8080/usuarios/cadastrar", {
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(usuario)
    })
        .then(function (res) {
            if (res.ok) {
                alertSucesso.classList.remove('hidden');
            } else {
                alertErro.classList.remove('hidden');
            }
            console.log(res);
        })
        .catch(function (err) {
            console.log(err);
            alertErro.classList.remove('hidden');
        });
}

form.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrar();
});

btnOk.addEventListener('click', function() {
    alertSucesso.classList.add('hidden');
    window.location.href = '/login';
});

btnErro.addEventListener('click', function() {
    alertErro.classList.add('hidden');
});
