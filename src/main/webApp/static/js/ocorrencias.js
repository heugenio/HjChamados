
/* global TipoMsg */

var lenDescricao;

$(document).ready(function () {
    $('#btn-search').click(function () {
        
        var fornecedor = $('#input-fornecedor').val();
        var unidade = $("#unidades option:selected").val();
        var status = $('input[name=toggle]:checked').val();

        var filtros = {
            "fornecedor": fornecedor,
            "unidades": unidade,
            "status": status
        };
        
        $.get('ocorrencias/lista/',filtros, function (dados) {
            $('#conteudo').html(dados);
        });
        
    });

    $('#btn-adcionar').click(function () {
        $('#modal-ocorrencias').modal('show');
        $.get('ocorrencias/novo', function (dados) {
            $('#conteudo-modal').html(dados);
        });
    });
    salvar();
});




function salvar() {

    $('#btn-salvar').click(function () {
        
        if(validarCampos() && lenDescricao <= 5000) {
        
            var ocorrencias = $("#form-cad-ocorrencias").serialize();
            
            $.post('ocorrencias/salvar', ocorrencias).done(function (retono, status, jqxhr) {

                $('#modal-ocorrencias').modal('hide');
                
                var fornecedor = $('#selectFornecedor option:selected').text();
                var unidade = $("#selectUnidade option:selected").val();
                var status = $('input[name=toggle]:checked').val();

                var filtros = {
                    "fornecedor": fornecedor,
                    "unidades": unidade,
                    "status": status
                };
                
                $.get('ocorrencias/lista/',filtros, function (dados) {
                    $('#conteudo').html(dados);
                });
                
                if(status) {
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de ocorrências", "Ocorrência cadastrada com sucesso!");
                }
                
            }).fail(function (retono) {
                centralMensagem(TipoMsg.ERRO, "Cadastro de ocorrências", "Um erro ocorreu! "+retono);
            });
            
        }
        
    });
};

function alterarOcorrencia(id) {
    $('#modal-ocorrencias').modal('show');
    $.get('ocorrencias/alterar/' + id, function (dados) {
        $('#selectTipoOcorrenciaDescricao').removeAttr("disabled");
        $('#conteudo-modal').html(dados);
    });
}


function validarCampos() {
    
    $("#pMsg").html("");
    
    if(Number.parseInt($("#selectOcorrenciasStatus option:selected").val()) === 11) {
        $("#selectOcorrenciasStatus").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Status é obrigatório!");
        return false;
    } else if(Number.parseInt($("#selectUnidade option:selected").val()) === 11) {
        $("#selectUnidade").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Unidade é obrigatória!");
        return false;
    } else if(Number.parseInt($("#selectTipoOcorrenciaDescricao option:selected").val()) === 11) {
        $("#selectTipoOcorrenciaDescricao").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Tipo ocorrência é obrigatório!");
        return false;
    } else if(Number.parseInt($("#selectFornecedor option:selected").val()) === 11) {
        $("#selectFornecedor").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Fornecedor é obrigatório!");
        return false;
    } else if($("#textAreaDescricao").val() === "") {
        $("#textAreaDescricao").focus();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Descricão é obrigatória!");
        return false;
    }
    
    $("#camposNaoPreenchidos").hide();
    return true;
}

function qtdCaracter(descricao) {
    var num = Number.parseInt(descricao.length);
    $("#caracterRestante").html("");
    if(num<=5000) {
        $("#dvCaracterRestante").show();
        $("#caracterRestante").append("Restante: "+(5000-num));
        lenDescricao = num;
    }
}


function changeSelects(qualSelect) {

    var idSelecSelecionado = $("#" + (qualSelect.toString()) + " option:selected").val();

    $.get('ocorrencias/changeSelects/' + Number.parseInt(idSelecSelecionado), function (listaTipoOcorrencia) {
        var selectbox = $('#selectTipoOcorrenciaDescricao');
        if (listaTipoOcorrencia !== null && listaTipoOcorrencia.length > 0) {
            selectbox.find('option').remove();
            $.each(listaTipoOcorrencia, function (i, d) {
                $('<option>').val(d.id).text(d.descricao).appendTo(selectbox);
            });
            selectbox.removeAttr("disabled");
        } else {
            selectbox.find('option').remove();
            $('<option disabled selected>').text("Tipos ocorrências").appendTo(selectbox);
            selectbox.prop("disabled", true);
        }

    });

}