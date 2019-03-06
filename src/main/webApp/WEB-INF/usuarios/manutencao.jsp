<%-- 
    Document   : manutencao
    Created on : 08/02/2019, 14:57:51
    Author     : Heugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<fmt:setLocale value = "pt_BR"/>

<jsp:include page="../fragments/cabecallho.jsp"/>
<!-- Inicio do conteudo -->  
<div class="container">
    <div class="row"> 
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="col-xs-12 col-sm-12 col-md-6" style="padding-top: 3px;">
                    <strong>Manutenção de Usuários</strong> 
                </div> 
                <div class="col-xs-12 col-sm-12 col-md-6">
                    <button id="btn-adcionar" class="btn btn-default pull-right btn-sm">Adicionar</button> 
                </div>                 
                <div class="clearfix"></div>
            </div> 
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6">
                        <input id="input-user" class="form-control" type="text"/>
                    </div>  
                    <div class="col-xs-12 col-sm-12 col-md-6">
                        <button id="btn-search" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>                        
                    </div>
                </div> 
                <div class="row" id="conteudo">

                </div> 
            </div>
        </div>
    </div>
</div>

<!-- inicio Modal gravar usuario -->
<div class="modal fade" tabindex="-1" role="dialog" id="modal-user">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Manutenção de Usuários</h4>
            </div>
            <div class="modal-body" id="conteudo-modal">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="button" id="btn-salvar" class="btn btn-primary">Salvar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Fim Conteudo -->  

<jsp:include page="../fragments/rodape.jsp"/>    
<script src="${path}/static/js/usuario.js"></script>
