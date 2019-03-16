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
                    <button type="button" name="btn1" id="btn1" class="btn btn-primary"></button>
                    <button type="button" name="btn2" id="btn2" class="btn btn-secondary" data-dismiss="modal"></button>
                </div>
            </div>
        </div>
    </div>
</html>

<!--<div class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div> /.modal-content 
  </div> /.modal-dialog 
</div> /.modal -->