$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-user').val();
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
    int_botoes_usuarios();
});

//Altera a formatação do campo cpf/cnpj conforme seleção.
function changeCampoCpfCnpj(value) {
    if(value.toString() === "cpf") {
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

function alteraUser(id) {
    $('#modal-fornecedor').modal('show');
    $.get('fornecedor/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        int_botoes_usuarios();
    });
}

var int_botoes_usuarios = function () {
    $('#btn-salvar').click(function () {
        var user = $("#form-cad-fornecedor").serialize();
        $.post('fornecedor/salvar', user).done(function (retono, status, jqxhr) {
            $('#modal-fornecedor').modal('hide');
            alert('Salvo com sucesso.');
        }).fail(function (retono) {
            alert('Falhou');
        });
    });
};