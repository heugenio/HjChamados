/**
 * @author Hallef
 * @since 07/03/2019
 * 
 * O arquivo possui dois métodos; um para mostrar a mensagem de sucesso,
 * e outro método para mostrar uma mensagem de falha.
 * 
 * */

/**
 * @param {Enum} TipoMsg 
 * @param {String} msgTitulo
 * @param {String} msgBody 
 * */
function centralMensagem(TipoMsg,msgTitulo,msgBody) {
    if(TipoMsg === 1) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        $("#msg").modal("show");
    } else if(TipoMsg === 2) {
        $("#titulo").text(msgTitulo);
        $("#corpo").text(msgBody);
        $("#tipoMsgBgColor").addClass("modal-header bg-danger");
        $("#msg").modal("show");
        
    }
}

var TipoMsg = {
    SALVAR: 1,
    ERRO: 2
};