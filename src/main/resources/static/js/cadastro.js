const form = document.getElementById("formCadastro");
const alertSucesso = document.getElementById('alertSucesso');
const btnOk = document.getElementById('btnOk');

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
                alert("Erro ao cadastrar. Verifique os dados e tente novamente.");
            }
            console.log(res);
        })
        .catch(function (err) {
            console.log(err);
            alert("Erro na conexão com o servidor.");
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
