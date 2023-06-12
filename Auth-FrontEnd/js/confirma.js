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

  