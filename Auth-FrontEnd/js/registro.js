async function verificaRegistro() {
  if (verificaEmail()) {
    if (verificaSenha()) {
      var resultadoCadastro = await cadastrandoUsuario();

      if (resultadoCadastro === true) {
        document.querySelector("p#mensagem_erro").innerHTML = "Cadastrado";
      } else {
        mensagemErro("Email já cadastrado");
      }
    }
  }
}

function verificaSenha() {
  var primeiraSenha = document.querySelector("input#Password_input").value;
  var segundaSenha = document.querySelector(
    "input#Confirm_password_input"
  ).value;

  var temConteudo = primeiraSenha !== "" && segundaSenha !== "" ? true : false;

  if (temConteudo) {
    if (primeiraSenha === segundaSenha) {
      return true;
    }
    mensagemErro("Senhas não conferem");
    return false;
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

function mensagemErro(mensagemErro) {
  document.querySelector("p#mensagem_erro").innerHTML = mensagemErro;
}

async function cadastrandoUsuario() {
  var emailInput = document.querySelector("input#email_input").value;
  var senhaInput = document.querySelector("input#Password_input").value;

  try {
    const user = {
      email: emailInput,
      password: senhaInput,
    };

    const response = await fetch("http://localhost:8080/api/v1/users/save/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });

    return await response.json();
  } catch (error) {
    console.error("Ocorreu um erro:", error);
  }
}
