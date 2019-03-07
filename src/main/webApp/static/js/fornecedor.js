/* global TipoMsg */

$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-fornecedor').val();
        $.get('fornecedor/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
        });
    });
    
    
    $('#btn-adcionar').click(function () {
        $('#modal-fornecedor').modal('show');
        $.get('fornecedor/novo', function (dados) {
            $('#conteudo-modal').html(dados);
            $('#inputCnpjCpf').attr('placeholder','CPF');
            $("#inputCnpjCpf").mask("000.000.000-00",{reverse: true});
            $('#inputTelefone').mask('(00)0000-0000');
            $('#inputCelular').mask('(00)00000-0000');
        });
    });
    salvar();
});

//Altera a formatação do campo cpf/cnpj conforme seleção.
function changeCampoCpfCnpj(idBtnRadio) {
    if(idBtnRadio.toString() === "cpf") {
        $('#inputCnpjCpf').val("");
        $('#inputCnpjCpf').focus();
        $('#inputCnpjCpf').attr('placeholder','CPF');
        $("#inputCnpjCpf").mask("000.000.000-00",{reverse: true});
    } else {
        $('#inputCnpjCpf').focus();
        $('#inputCnpjCpf').val("");
        $('#inputCnpjCpf').attr('placeholder','CNPJ');
        $("#inputCnpjCpf").mask("00.000.000/0000-00",{reverse: true});
    }
}

function alteraFornecedor(id) {
    $('#modal-fornecedor').modal('show');
    $.get('fornecedor/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        $('#inputCnpjCpf').attr('placeholder','CPF');
        $("#inputCnpjCpf").mask("000.000.000-00",{reverse: true});
        $('#inputTelefone').mask('(00)0000-0000');
        $('#inputCelular').mask('(00)00000-0000');
    });
}

function salvar() {
    $('#btn-salvar').click(function () {
        var fornecedor = $("#form-cad-fornecedor").serialize();
        $.post('fornecedor/salvar', fornecedor).done(function (retono, status, jqxhr) {
            $.get('fornecedor/lista/',function (dados) {
                $('#conteudo').html(dados);
            });
            $('#modal-fornecedor').modal('hide');
            if(retono.toString() === "true") {
                centralMensagem(TipoMsg.SALVAR,"Cadastro de fornecedor","Fornecedor cadastrado com sucesso!");
            }
        }).fail(function (retono) {
            alert('Falhou ');
        });
    });
};