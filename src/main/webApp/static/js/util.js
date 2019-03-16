/**
 * @author Hallef
 * @since 07/03/2019
 * */

/**
 * @param {Enum} TipoMsg 
 * @example TipoMsg === SALVAR {msg salvar === 1} TipoMsg === ERRO {msg de erro === 2}
 * @param {String} msgTitulo
 * @param {String} msgBody 
 * */
function centralMensagemInfo(TipoMsg,msgTitulo,msgBody) {
    if(TipoMsg === 1) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        $("#btn1").hide();
        $("#btn2").html("Fechar");
        $("#msg").modal("show");
    } else if(TipoMsg === 2) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        $("#tipoMsgBgColor").addClass("modal-header bg-danger");
        $("#btn1").hide();
        $("#btn2").html("Fechar");
        $("#msg").modal("show");
    }
}


/**
 * @param {Enum} TipoMsg 
 * @example TipoMsg === SALVAR {msg salvar === 1} TipoMsg === ERRO {msg de erro === 2}
 * O parametro textBtn1 e textBtn2 é para mostrar o texto desejado nos btns
 * @param {String} msgTitulo
 * @param {String} msgBody
 * @param {String} textBtn1 
 * @param {String} textBtn2  
 * 
 * */
function centralMensagem(TipoMsg,msgTitulo,msgBody,textBtn1,textBtn2) {
    if (TipoMsg === 1) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        if((textBtn1!==null && textBtn1!=="") && (textBtn2!==null && textBtn2!=="")) {
            $("#btn1").html(textBtn1);
            $("#btn2").html(textBtn2);
        }
        $("#msg").modal("show");
    } else if(TipoMsg === 2) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        if((textBtn1!==null && textBtn1!=="") && (textBtn2!==null && textBtn2!=="")) {
            $("#btn1").html(textBtn1);
            $("#btn2").html(textBtn2);
        }
        $("#msg").modal("show");
    }
    
}

/*
 * 
 * @type Enum
 */
var TipoMsg = {
    SALVAR: 1,
    ERRO: 2
};

/*
 * 
 * @param {String} cpf
 * @returns {Boolean} 
 */
function validarCpf(cpf) {

    var soma = 0;
    var resto;
    
    cpf = cpf.replace(/[^\d]+/g, '');

    if (cpf === '')
        return false;
    if(cpf.length !== 11)
        return false;
    
    if (cpf === "00000000000" ||
            cpf === "11111111111" ||
            cpf === "22222222222" ||
            cpf === "33333333333" ||
            cpf === "44444444444" ||
            cpf === "55555555555" ||
            cpf === "66666666666" ||
            cpf === "77777777777" ||
            cpf === "88888888888" ||
            cpf === "99999999999")
        return false;

    for (var i = 1; i <= 9; i++)
        soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
    resto = (soma * 10) % 11;
    
    if ((resto === 10) || (resto === 11))
        resto = 0;
    if (resto !== parseInt(cpf.substring(9, 10)))
        return false;

    soma = 0;
    for (var i = 1; i <= 10; i++)
        soma = soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))
        resto = 0;
    if (resto !== parseInt(cpf.substring(10, 11)))
        return false;
    return true;
}

/*
 * 
 * @param {String} cnpj
 * @returns {Boolean}
 */
function validarCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g, '');

    if (cnpj === '')
        return false;

    if (cnpj.length !== 14)
        return false;

    // Elimina CNPJs invalidos conhecidos
    if (cnpj === "00000000000000" ||
            cnpj === "11111111111111" ||
            cnpj === "22222222222222" ||
            cnpj === "33333333333333" ||
            cnpj === "44444444444444" ||
            cnpj === "55555555555555" ||
            cnpj === "66666666666666" ||
            cnpj === "77777777777777" ||
            cnpj === "88888888888888" ||
            cnpj === "99999999999999")
        return false;

    // Valida DVs
    var tamanho = cnpj.length - 2;
    var numeros = cnpj.substring(0, tamanho);
    var digitos = cnpj.substring(tamanho);
    var soma = 0;
    var pos = tamanho - 7;
    
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    var resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    
    if (resultado !== parseInt(digitos.charAt(0))) {
        return false;
    }
    
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) {
            pos = 9;
        }
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    
    if (resultado !== parseInt(digitos.charAt(1)))
        return false;
    return true;
}


var languagePtBr = {"sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",

    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",

    "sEmptyTable": "Nenhum registro encontrado",

    "sInfoFiltered": "(Filtrados de _MAX_ registros)",

    "sInfoPostFix": "",

    "sInfoThousands": ".",

    "sLengthMenu": "_MENU_ resultados por página",

    "sLoadingRecords": "Carregando...",

    "sProcessing": "Processando...",

    "sZeroRecords": "Nenhum registro encontrado",

    "sSearch": "Pesquisar",

    "oPaginate": {

        "sNext": "Próximo",

        "sPrevious": "Anterior",

        "sFirst": "Primeiro",

        "sLast": "Último"

    },

    "oAria": {

        "sSortAscending": ": Ordenar colunas de forma ascendente",

        "sSortDescending": ": Ordenar colunas de forma descendente"

    }

};