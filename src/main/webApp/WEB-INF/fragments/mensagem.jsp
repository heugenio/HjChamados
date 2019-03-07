<%-- 
    Document   : mensagem
    Created on : 07/03/2019, 10:58:11
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="modal fade" id="msg" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div id="tipoMsgBgColor" class="modal-header bg-success">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <b class="modal-title" id="titulo"></b>
                </div>
                <div class="modal-body">
                    <p id="corpo"></p> 
                </div>
                <div class="modal-footer">
                    <button type="button" name="btnFechar" id="btnFechar" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>
</html>
