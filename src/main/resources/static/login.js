const form = document.getElementById("formLogin");

function logar() {
    const usuarioLogin = {
        login: document.getElementById("login").value,
        senha: document.getElementById("password").value
    };

    fetch("http://localhost:8080/usuarios/logar", {
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(usuarioLogin)
    })
        .then(async function(res) {
            const data = await res.text();

            if (res.ok) {
                console.log("Login sucesso:", data);
                alert(data);
                window.location.href = '/home';
            } else {
                console.warn("Erro de login:", res.status, data);
                alert(data);
            }
        })
        .catch(function(err) {
            console.error("Erro na requisição:", err);
            alert("Erro ao conectar com o servidor.");
        });
}

form.addEventListener('submit', function (event) {
    event.preventDefault();
    logar();
});