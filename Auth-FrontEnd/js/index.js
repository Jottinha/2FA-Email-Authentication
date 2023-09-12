function trocandoIcone() {
  var tipoSenha = document.querySelector("input#Password_input");
  var icone = document.querySelector("ion-icon#icon_password");

  if (tipoSenha.type === "password") {
    tipoSenha.type = "text";
    icone.name = "eye-outline";
  } else {
    tipoSenha.type = "password";
    icone.name = "eye-off-outline";
  }
}

function verificaRegistro() {
  if (verificaEmail()) {
    if (verificaSenha()) {
      mensagemErro("");
      verificandoLogin();
    }
  }
}

function verificaSenha() {
  var senha = document.querySelector("input#Password_input").value;

  var temConteudo = senha !== "" ? true : false;

  if (temConteudo) {
    return true;
  }
  mensagemErro("Senha não pode estar vazia");
  return false;
}

function verificaEmail() {
  var email = document.querySelector("input#email_input").value;
  return eValidoEmail(email);
}

function eValidoEmail(email) {
  var regex = /^[\w.-]+@[a-zA-Z_-]+(?:\.[a-zA-Z]+)+$/;
  var eValido = regex.test(email);

  if (eValido) {
    return true;
  }
  mensagemErro("Email invalido");
  return false;
}

async function verificandoLogin() {
  try {
    const loginUser = {
      email: document.querySelector("input#email_input").value,
      password: document.querySelector("input#Password_input").value,
    };
    const response = await fetch("http://localhost:8080/api/v1/users/login/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(loginUser),
    });

    const loginStatus = await response.json();

    if (loginStatus === true) {
      const emailToSend = loginUser.email;
      localStorage.setItem("email", emailToSend);

      window.location.href = "confirma.html";
    } else {
      mensagemErro("Usuário ou senha incorretos");
    }
  } catch (error) {
    mensagemErro("Ocorreu um erro, tente mais tarde");
    console.error("Ocorreu um erro:", error);
  }
}

function mensagemErro(mensagemErro) {
  document.querySelector("p#mensagem_erro").innerHTML = mensagemErro;
}
