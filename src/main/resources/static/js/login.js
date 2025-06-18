const form = document.getElementById("formLogin");

function logar(){
    const usuarioLogin = {
        login: document.getElementById("login").value,
        senha: document.getElementById("password").value
    };


    fetch("http://localhost:8080/usuarios/logar", {
        headers: {
            'Accept':'application/json',
            "Content-Type": "application/json"
        },

        method: "POST",
        body:JSON.stringify(usuarioLogin)

    })

    .then(function(res){
    console.log("Resposta:", res);
    })
    .catch(function(res){
        console.log("Erro:", res);
    })

}

form.addEventListener('submit', function (event) {
    event.preventDefault();
    logar();
});