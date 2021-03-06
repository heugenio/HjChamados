

/* global TipoMsg */

$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-tiposOcorrencia').val();
        $.get('tiposocorrencia/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
            initDataTable();
        });
    });

    $('#btn-adcionar').click(function () {
        $('#modal-tiposOcorrencia').modal('show');
        $.get('tiposocorrencia/novo', function (dados) {
            $('#conteudo-modal').html(dados);
        });
    });
    int_botoes_tiposocorrencia();
});

function alteraTiposOcorrencia(id) {
    $('#modal-tiposOcorrencia').modal('show');
    $.get('tiposocorrencia/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
    });
    //int_botoes_tiposocorrencia();
}

var int_botoes_tiposocorrencia = function () {
    
    $('#btn-salvar').click(function () {
        
        if(validarTipoOcorrencia()) {
        
            var user = $("#form-cad-tiposocorrencia").serialize();
            
            $.post('tiposocorrencia/salvar', user).done(function (retorno, status, jqxhr) {
                
                $('#modal-tiposOcorrencia').modal('hide');
                
                $.get('tiposocorrencia/lista/', function (dados) {
                    $('#conteudo').html(dados);
                    initDataTable();
                });
                
                if(status && retorno==="") {
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de Tipo de ocorrência", "Salvo com sucesso!");
                } else if(status && retorno!=="") {
                    centralMensagem(TipoMsg.ERRO, "Cadastro de Tipo de ocorrência", retorno);
                }
                
            }).fail(function (retono) {
                centralMensagem(TipoMsg.ERRO, "Cadastro de Tipo de ocorrência", "Um erro ocorreu! "+retono);
            });
            
        }
        
    });
};

function validarTipoOcorrencia() {
    
    $("#campoDescricao").html("");
    $("#campoPrazo").html("");
    
    if($("#inputDescricao").val() === "") {
        $("#camposNaoPreenchidos").show();
        $("#campoDescricao").append('Por favor, preencha o campo descrição!');
        $("#inputDescricao").focus();
        return false;
        
    } else if($("#inputPrazo").val() === "" || Number.parseInt($("#inputPrazo").val()) <= 0) {
        $("#camposNaoPreenchidos").show();
        $("#campoPrazo").append('Por favor, preencha o campo prazo corretamente!');
        $("#inputPrazo").focus();
        return false;
    }
    $("#camposNaoPreenchidos").hide();
    return true;
}

var initDataTable = function (){
    $('#tabelaTiposOcorrencia').DataTable( {
        destroy: true,
        language:languagePtBr
    });
}