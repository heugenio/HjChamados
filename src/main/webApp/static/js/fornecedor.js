/* global TipoMsg, languagePtBr */

var tiposOcorerencias = [];

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
            tiposOcorerencias = [];
        });
    });
    
    salvar();
});

function popularTabela() {
    
    var tipo = {
        "id":parseInt($("#tipoOcorrenciaDescricao option:selected").val()),
        "descricao":$("#tipoOcorrenciaDescricao option:selected").text()
    };
    
    tiposOcorerencias.push(tipo);
    initDataTable(tiposOcorerencias);
}

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

function alteraFornecedor(id,nomeFornecedorGrid) {
    $('#modal-fornecedor').modal('show');
    $.get('fornecedor/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        if($('#inputCnpjCpf').val().length === 14) {
            $('#inputCnpjCpf').attr('placeholder','CPF');
            $("#inputCnpjCpf").mask("000.000.000-00",{reverse: true});
        } else {
            $('#inputCnpjCpf').attr('placeholder','CNPJ');
            $("#inputCnpjCpf").mask("00.000.000/0000-00",{reverse: true});
        }
        $('#inputTelefone').mask('(00)0000-0000');
        $('#inputCelular').mask('(00)00000-0000');
        tiposOcorerencias = [];
        
        $.get("fornecedor/buscarFornecedorporId/"+id,function (fornecedor){
            tiposOcorerencias = fornecedor.listTiposOcorrencias;
            initDataTable(tiposOcorerencias);
        });
        
        
        popularSelectUsuarioFornecedor(nomeFornecedorGrid);
        
    });
}

function salvar() {
    
    $('#btn-salvar').click(function () {

        var fornecedor = $("#form-cad-fornecedor").serializeArray();
        
        var idTipod = [];
        
        for(i=0; i<tiposOcorerencias.length; i++) {
            idTipod.push(tiposOcorerencias[i].id);
        }
        var somenteNome = $("#usuarioFornecedor option:selected").text();
        //var resultado = somenteNome.substring(0,somenteNome.indexOf("-"));
        fornecedor[1].value = somenteNome;
        fornecedor[8].value = idTipod;
        
        if (validarCampos(fornecedor)) {

            $.post('fornecedor/salvar', fornecedor).done(function (retono, status, jqxhr) {

                $.get('fornecedor/lista/', function (dados) {
                    $('#conteudo').html(dados);
                });
                
                $('#modal-fornecedor').modal('hide');
                
                if (status) {
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de fornecedor", "Fornecedor cadastrado com sucesso!");
                }
                
            }).fail(function (retono) {
                centralMensagem(TipoMsg.ERRO, "Cadastro de fornecedor", "Erro ao cadastrar fornecedor " + retono);
            });
        }
    });
};

function validarCampos(array) {
    
    $("#campoNome").html("");
    var validar = true;
    
    if (array[1].value === "") {

        $("#camposNaoPreenchidos").show();
        $("#campoNome").append('Por favor, preencha o campo nome!');
        $("#inputNome").focus();
        validar = false;
        
        //$("#campoNome").append('<span id="add_here" class="glyphicon glyphicon-alert bg-danger"></span>');
        
    } else if (array[1].value.length < 3) {
        
        $("#campoNome").html("");
        $("#camposNaoPreenchidos").show();
        $("#campoNome").append('O campo nome precisa ser maior ou iagual a três!');
        $("#inputNome").focus();
        validar = false;
        
    } else if(array[3].value.length === 14) {
        
        $("#campoCpfCnpj").html("");
        var cpf = array[3].value;
        
        if(validarCpf(cpf)) {
            validar = true;
        } else {
            $("#camposNaoPreenchidos").show();
            $("#campoCpfCnpj").append('O CPF é inválido!');
            validar = false;
        }
        
    } else if(array[3].value.length === 18) {
        
        $("#campoCpfCnpj").html("");
        var cnpj = array[3].value;

        if(validarCNPJ(cnpj)) {
            validar = true;
        } else {
            $("#camposNaoPreenchidos").show();
            $("#campoCpfCnpj").append('O CNPJ é inválido!');
            validar = false;
        }
        
    } 
    if(($('#cpf').is(':checked') && array[3].value === "") || ($('#cpf').is(':checked') && array[3].value.length < 14) ) {
        $("#campoCpfCnpj").html("");
        $("#camposNaoPreenchidos").show();
        $("#campoCpfCnpj").append('O CPF é inválido!');
        validar = false;
    } else if($('#cnpj').is(":checked") && array[3].value === "" || 
             ($('#cnpj').is(':checked') && (array[3].value.length > 14 && array[3].value.length < 18) || 
             ($('#cnpj').is(":checked") && array[3].value.length < 18))) {
        $("#campoCpfCnpj").html("");
        $("#camposNaoPreenchidos").show();
        $("#campoCpfCnpj").append('O CNPJ é inválido!');
        validar = false;
    }
    
    return validar;
}
var initDataTable = function (tipo){
     $('#tbTipoOcorrencia').DataTable( {
        destroy: true,
        data: tipo,
        language:languagePtBr,
        paging:   false,
        ordering: false,
        info:     false,
        columns: [
            {data:"descricao", title: "Tipo da ocorrência" },
            {data:"id", title: "Ações", mRender:function (data,type,row) {
                return "<a href='#' data-toggle='tooltip' title='Clique para remover' class='btnRemover' data-id='"+data+"'> <i class='glyphicon glyphicon-remove'></i></a>";
            }}
        ],
        columnDefs: [
            {
                targets:1,
                className: "text-center",
                width: "4%"
            }
        ],
        searching: false 
    });
    removerTipoOcorrencia();
};

function removerTipoOcorrencia() {

    $(".btnRemover").on("click",function () {
        var id = $(this).data("id");
        var arrayTipoA = tiposOcorerencias.filter(function (element, index, array) {
            if(parseInt(element.id) === id) {
                return array.indexOf(index);
            }
        });
        
        var arrayTipoB = tiposOcorerencias.filter(item => item.id !== arrayTipoA[0].id);
        tiposOcorerencias = arrayTipoB;
        
        initDataTable(tiposOcorerencias);
    });
}


function popularSelectUsuarioFornecedor(nomeFornecedorGrid) {

    var nomeFornecedorQueEstaNaLista = "";
    
    console.log(nomeFornecedorGrid);

    $.get('fornecedor/popularSelectUsuForn', function (listaTipoOcorrencia) {
        var selectbox = $('#usuarioFornecedor');
        if (listaTipoOcorrencia !== null && listaTipoOcorrencia.length > 0) {
            selectbox.find('option').remove();
            $('<option>').text(nomeFornecedorGrid).appendTo(selectbox);//.val(idForn)
            $.each(listaTipoOcorrencia, function (i, d) {
                nomeFornecedorQueEstaNaLista = d.nomeUsuario;
                if(!nomeFornecedorGrid.includes(d.nomeUsuario.toString())) {//parseInt(idForn)!==parseInt(parseInt(d.idUsuario))) && 
                    $('<option>').text(nomeFornecedorQueEstaNaLista.toString().trim()).appendTo(selectbox);//.val(d.idUsuario)
                }
            });
            selectbox.removeAttr("disabled");
        }

    });
}