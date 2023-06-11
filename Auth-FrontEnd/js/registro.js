function verificaRegistro(){
    if (verificaEmail()){
        if (verificaSenha()){
            document.querySelector("p#mensagem_erro").innerHTML = "";
            //chama função para mandar para o back o email e a senha  
        }
    }
}

function verificaSenha(){
    var primeiraSenha = document.querySelector("input#Password_input").value;
    var segundaSenha = document.querySelector("input#Confirm_password_input").value;

    var temConteudo = (primeiraSenha !== "" && segundaSenha !== "") ? true : false;

    if (temConteudo){
        if (primeiraSenha === segundaSenha){
            return true;
        }
        mensagemErro("Senhas não conferem");
        return false;
    }
    mensagemErro("Senha não pode estar vazia");
    return false;
}

function verificaEmail(){
    var email = document.querySelector("input#email_input").value;
    return eValidoEmail(email);
    //condição e metodo que busca email no back

}

function eValidoEmail(email){
    var regex = /^[\w.-]+@[a-zA-Z_-]+(?:\.[a-zA-Z]+)+$/;
    var eValido = regex.test(email);

    if (eValido){
        return true;
    }
    mensagemErro("Email invalido")
    return false;
}

function mensagemErro(mensagemErro){
    document.querySelector("p#mensagem_erro").innerHTML = mensagemErro;
}
