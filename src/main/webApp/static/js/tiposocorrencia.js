/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-tiposOcorrencia').val();
        $.get('tpoc/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
        });
    });

    $('#btn-adcionar').click(function () {
        $('#modal-tiposOcorrencia').modal('show');
        $.get('tpoc/novo', function (dados) {
            $('#conteudo-modal').html(dados);
            int_botoes_tiposocorrencia();
        });
    });

});

function alteraTiposOcorrencia(id) {
    $('#modal-tiposOcorrencia').modal('show');
    $.get('tpoc/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        int_botoes_tiposocorrencia();
    });
}

var int_botoes_tiposocorrencia = function () {
    $('#btn-salvar').click(function () {
        var user = $("#form-cad-tiposocorrencia").serialize();
        $.post('tpoc/salvar', user).done(function (retono, status, jqxhr) {
            $('#modal-tiposOcorrencia').modal('hide');
            alert('Salvo com sucesso.');
        }).fail(function (retono) {
            alert('Falhou');
        });
    });
}