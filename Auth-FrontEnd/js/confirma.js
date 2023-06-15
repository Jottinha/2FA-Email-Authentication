
document.addEventListener('DOMContentLoaded', function() {
    const email = localStorage.getItem('email');
    enviarCodigo(email);
});

// Limpe os dados do localStorage após usá-los (opcional)
localStorage.removeItem('data');

function validarNumero(number, nextId) {
    var input = document.getElementById(number.id);
    var valor = input.value;

    var regexNumeros = /^[0-9]+$/;

    if (regexNumeros.test(valor)) {
        if (nextId && nextId.trim() !== ''){
            var futereFocus = document.getElementById(nextId);
            futereFocus.focus();
        }
    } else {
        input.value = input.value.replace(/\D/g, '');
    }
}

function apagarNumero(selectText, event){
    var keyCode = event.keyCode || event.which;
  
    if (keyCode === 8 && selectText.value === '') {
      var previousInput = selectText.previousElementSibling;
      if (previousInput) {
        previousInput.focus();
      }
    }
}

function verificandoCodigo(){
    var primeiroCodigo = document.querySelector('input#first_number').value;
    var segundoCodigo = document.querySelector('input#second_numebr').value;
    var terceiroCodigo = document.querySelector('input#third_number').value;
    var quartoCodigo = document.querySelector('input#fourth_number').value;


    var teste = primeiroCodigo + segundoCodigo + terceiroCodigo + quartoCodigo;
    console.log(teste);

    // TODO: O codigo esta funcionando corretamente e agora é implementar alguma mensagem ou tela que o usuario foi corretamente cadastrado
}

async function enviarCodigo(emailInput){
    try {
        const envioCodigo = {
            email:emailInput
        };
        const response = await fetch ('http://localhost:8080/api/v1/users/authentication/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(envioCodigo)
        });

        return await response.json();
    } catch (error) {
        console.error('Ocorreu um erro:', error);
    }

}
  