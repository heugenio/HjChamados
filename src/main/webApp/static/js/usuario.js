/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-user').val();
        $.get('user/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
        });
    });

    $('#btn-adcionar').click(function () {
        $('#modal-user').modal('show');
        $.get('user/novo', function (dados) {
            $('#conteudo-modal').html(dados);
        });
    });

    int_botoes_usuarios();
});

function alteraUser(id) {
    $('#modal-user').modal('show');
    $.get('user/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
    });
    int_botoes_usuarios();
}

var int_botoes_usuarios = function () {
    $('#btn-salvar').click(function () {
        var user = $("#form-cad-user").serialize();
        $.post('user/salvar', user).done(function (retono, status, jqxhr) {
            $('#modal-user').modal('hide');
            alert('Salvo com sucesso.');
        }).fail(function (retono) {
            alert('Falhou');
        });
    });
}

