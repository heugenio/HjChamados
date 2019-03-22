
/* global TipoMsg */

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

        $.get('ocorrencias/lista/', filtros, function (dados) {
            $('#conteudo').html(dados);
            if (status === "Fechado") {
                $("#dataFechamento").show();
            }
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

        if (validarCampos()) {

            var ocorrencias = $("#form-cad-ocorrencias").serialize();//

            $("#div_gif").show();

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

                $.get('ocorrencias/lista/', filtros, function (dados) {
                    $('#conteudo').html(dados);
                });

                if (status) {
                    $("#div_gif").hide();
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de ocorrências", "Ocorrência cadastrada com sucesso! E-mail enviado");
                }

            }).fail(function (retono) {
                //console.log(retono.responseJSON.message);
                $("#div_gif").hide();
                $("#corpo").css('color','red');
                $('#modal-ocorrencias').modal('hide');
                centralMensagem(TipoMsg.ERRO, "Cadastro de ocorrências", "Um erro ocorreu (" + retono.responseJSON.message+")");
            });

        }

    });
}
;

function alterarOcorrencia(id) {
    $('#modal-ocorrencias').modal('show');
    $.get('ocorrencias/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        $('#selectTipoOcorrenciaDescricao').removeAttr("disabled");
    });
}


function validarCampos() {

    $("#pMsg").html("");

    if (Number.parseInt($("#selectOcorrenciasStatus option:selected").val()) === 11) {
        $("#selectOcorrenciasStatus").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Status é obrigatório!");
        return false;
    } else if (Number.parseInt($("#selectUnidade option:selected").val()) === 11) {
        $("#selectUnidade").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Unidade é obrigatória!");
        return false;
    } else if (Number.parseInt($("#selectTipoOcorrenciaDescricao option:selected").val()) === 11) {
        $("#selectTipoOcorrenciaDescricao").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Tipo ocorrência é obrigatório!");
        return false;
    } else if (Number.parseInt($("#selectFornecedor option:selected").val()) === 11) {
        $("#selectFornecedor").focus().select();
        $("#camposNaoPreenchidos").show();
        $("#pMsg").append("Fornecedor é obrigatório!");
        return false;
    } else if ($("#textAreaDescricao").val() === "") {
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
    if (num <= 5000) {
        $("#dvCaracterRestante").show();
        $("#caracterRestante").append("Restante: " + (5000 - num));
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

function alterarStatus(idOcorrencia, staOcorrencia) {

    centralMensagem(TipoMsg.SALVAR, "Manutenção de ocorrência", "Deseja finalizar a ocorrência?","SIM","NÃO");

    $("#btn1").click(function () {
        
        $("#btn1").prop("disabled", true);
        
        $.get("ocorrencias/updateStatusOcorrencia/" + parseInt(idOcorrencia) + "/" + staOcorrencia).done(function (retorno, status, jqxhr) {
            
            if(status) {
                
                var array = JSON.parse(retorno);

                $('#tbOcorrencias tbody > tr').remove();
                

                $("#fechado").prop("checked", true);
                var status = $('input[name=toggle]:checked').val();
                $("#dataFechamento").show();
                
                $("#tbOcorrencias").addClass("table table-bordered table-condensed table-hover");
                
                var dados = "<tr>"+ "<td>"+ array[0] +"</td>"+ 
                                    "<td>"+ array[1] +"</td>"+ 
                                    "<td>"+array[2]  +"</td>"+ 
                                    "<td><span class='label label-danger'>"+array[3]+"</span></td>"+
                                    "<td>"+array[4]+"</td>"+
                                    "<td>"+array[5]+"</td>"+
                                    "<td>"+array[6]+"</td>"+
                                    "<td style='text-align: center'><a class='disabled'><i class='glyphicon glyphicon-ban-circle'></i></a></td>"+
                                    "<td style='text-align: center'><a class='disabled'><i class='glyphicon glyphicon-ban-circle'></i></a></td>"+
                        
                                    +"</tr>";
                
                
                $("#tbOcorrencias tbody").append(dados);
               
                $("#btn1").prop("disabled", false);
                $('#msg').modal('hide');
                
            }

        }).fail(function (retorno) {
            $('#msg').modal('hide');
            $("#btn1").prop("disabled", false);
            centralMensagem(TipoMsg.ERRO, "Cadastro de ocorrências", "Um erro ocorreu! " + retorno);
        });
        
    });
}