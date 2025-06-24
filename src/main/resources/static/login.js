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
        .then(async function(res) {
            const data = await res.text();

            if (res.ok) {
                console.log("Login sucesso:", data);
                window.location.href = '/home';
            } else {
                console.log("Falha:", data);
                alert("Login ou senha incorretos.");
            }
        })

        .catch(function(res){
            console.log("Erro:", res);
        })

}

form.addEventListener('submit', function (event) {
    event.preventDefault();
    logar();
});