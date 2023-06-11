function verificaRegistro(){
    if (verificaEmail()){
        if (verificaSenha()){
            document.querySelector("p#mensagem_erro").innerHTML = "";
            //chama função para mandar para o back o email e a senha  
        }else{
            document.querySelector("p#mensagem_erro").innerHTML = "Senhas não conferem"
        }
    }else{
        document.querySelector("p#mensagem_erro").innerHTML = "Email invalido"
    }
}

function verificaSenha(){
    var primeiraSenha = document.querySelector("input#Password_input").value;
    var segundaSenha = document.querySelector("input#Confirm_password_input").value;
    return (primeiraSenha === segundaSenha) ? true : false;
}

function verificaEmail(){
    var email = document.querySelector("input#email_input").value;
    return eValidoEmail(email);
    //condição e metodo que busca email no back

}

function eValidoEmail(email){
    var regex = /^[\w.-]+@[a-zA-Z_-]+(?:\.[a-zA-Z]+)+$/;
    var eValido = regex.test(email);
    return (eValido) ? true : false;
}