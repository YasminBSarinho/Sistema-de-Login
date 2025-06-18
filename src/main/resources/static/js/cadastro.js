const form = document.getElementById("formCadastro");


function cadastrar(){
    const usuario = {
        nome: document.getElementById("nome").value,
        login: document.getElementById("login").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("password").value
    };

    fetch("http://localhost:8080/usuarios/cadastrar", {
        headers: {
            'Accept':'application/json',
            "Content-Type": "application/json"
        },

        method: "POST",
        body:JSON.stringify(usuario)

    })

    .then(function(res){console.log(res)})
    .catch(function(res){console.log(res)})

};

form.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrar();
});

