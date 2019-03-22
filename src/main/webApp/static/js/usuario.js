
/* global TipoMsg */

$(document).ready(function () {
    $('#btn-search').click(function () {
        var param = $('#input-user').val();
        $.get('usuario/lista/' + param, function (dados) {
            $('#conteudo').html(dados);
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
            console.log(dados);
            
            for(i=0;i<dados.length;i++) {
                console.log(dados[i].codigo+" "+dados[i].nome);
                $("#" + dados[i].nome).prop('checked', true);
            }
            
//            $("#listaBox input:checkbox").each(function (i) {
//                if ((parseInt(dados[i].codigo) === parseInt(this.value)) && (dados[i].nome.toString() === this.id.toString())) {
//                    console.log(dados[i].codigo + " " + dados[i].nome);
//                    $("#" + this.id.toString()).prop('checked', true);
//                }
//            });
        });
        
    });
}

function  salvar() {
    
    $('#btn-salvar').click(function () {
        
        var user = $("#form-cad-user").serializeArray();
        
        var grupos = [];
        $('#listaBox input:checked').each(function() {
            grupos.push(parseInt(this.value));
        });
        
        if(validarUsuario()) {
        
            $.post('usuario/salvar/'+grupos, user).done(function (retorno, status, jqxhr) { 

                $('#modal-user').modal('hide');

                $.get('usuario/lista/', function (dados) {
                    $('#conteudo').html(dados);
                });

                if(status) {
                    centralMensagem(TipoMsg.SALVAR, "Cadastro de usuário", "Usuário cadastrado com sucesso!");
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
    
    if($("#inputNome").val() === "") {
        $("#camposNaoPreenchidos").show();
        $("#campoNome").append('Por favor, preencha o campo nome!');
        $("#inputNome").focus();
        return false;
        
    } else if($("#inputLogin").val() === "") {
        $("#camposNaoPreenchidos").show();
        $("#campoLogin").append('Por favor, preencha o campo login!');
        $("#inputLogin").focus();
        return false;
        
    } else if($("#inputEmail3").val() === "") {
        $("#camposNaoPreenchidos").show();
        $("#campoLogin").append('Por favor, preencha o campo email!');
        $("#inputEmail3").focus();
        return false;
        
    } else if($("#inputPassword3").val() === "") {
        $("#camposNaoPreenchidos").show();
        $("#campoPassword").append('Por favor, preencha o campo password!');
        $("#inputPassword3").focus();
        return false;
    }
    $("#camposNaoPreenchidos").hide();
    return true;
}

