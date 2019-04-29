
/* global TipoMsg */

$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-user').val();
        $.get('usuario/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
            initDataTable();
        });
    });

    $('#btn-adcionar').click(function () {
        $('#modal-user').modal('show');
        $.get('usuario/novo', function (dados) {
            $('#conteudo-modal').html(dados);
        });
    });

    salvar();
});

function alteraUser(id) {
    $('#modal-user').modal('show');
    $.get('usuario/alterar/' + id, function (dados) {
        $('#conteudo-modal').html(dados);
        
        $.get('usuario/nomeGrupo/'+id,function (dados) {
            for(i=0;i<dados.length;i++) {
                $("#" + dados[i].nome).prop('checked', true);
            }
        });
        
    });
}

function  salvar() {
    
    $('#btn-salvar').click(function () {
        
        var user = $("#form-cad-user").serializeArray();
        
        grupos = [];
        
        $('#listaBox input:checked').each(function() {
            grupos.push(parseInt(this.value));
        });
        
        if(validarUsuario()) {
        
            $.post('usuario/salvar/'+grupos, user).done(function (retorno, status, jqxhr) {

                $('#modal-user').modal('hide');

                $.get('usuario/lista/', function (dados) {
                    $('#conteudo').html(dados);
                    initDataTable();
                });

                if(status && retorno==="") {
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de usuário", "Salvo com sucesso!");
                } else if(status && retorno!=="") {
                    centralMensagem(TipoMsg.ERRO, "Cadastro de usuário", retorno);
                }

            }).fail(function (retorno) {
                centralMensagem(TipoMsg.ERRO, "Cadastro de usuário", retorno);
            });
            
        }
        
    });
};

function validarUsuario() {

    $("#campoNome").html("");
    $("#campoLogin").html("");
    $("#campoEmail").html("");
    $("#campoPassword").html("");
    $("#divGrupos").html("");
    
    if($("#inputNome").val() === "" || $("#inputNome").val().length < 3) {
        $("#camposNaoPreenchidos").show();
        $("#campoNome").append('Por favor, preencha o campo nome!');
        $("#inputNome").focus();
        return false;
        
    } else if($("#inputLogin").val() === "" || $("#inputLogin").val().length < 3) {
        $("#camposNaoPreenchidos").show();
        $("#campoLogin").append('Por favor, preencha o campo login!');
        $("#inputLogin").focus();
        return false;
        
    } else if($("#inputEmail3").val() === "" || !$("#inputEmail3").val().includes("@") || !$("#inputEmail3").val().includes(".com")) {
        $("#camposNaoPreenchidos").show();
        $("#campoLogin").append('Por favor, preencha o campo email!');
        $("#inputEmail3").focus();
        return false;
        
    } else if($("#inputPassword3").val() === "" || $("#inputPassword3").val().length < 4) {
        $("#camposNaoPreenchidos").show();
        $("#campoPassword").append('Por favor, preencha o campo password!');
        $("#inputPassword3").focus();
        return false;
    } else if(grupos.length === 0) {
        $("#camposNaoPreenchidos").show();
        $("#divGrupos").append("É necessário selecionar um grupo");
        return false;
    }
    $("#camposNaoPreenchidos").hide();
    return true;
}

var initDataTable = function (){
    $('#tabelaUsuarios').DataTable( {
        destroy: true,
        language:languagePtBr
    });
}