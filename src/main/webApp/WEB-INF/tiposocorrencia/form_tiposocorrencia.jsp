<%-- 
    Document   : form_tiposocorrencia
    Created on : 28/02/2019, 15:47:57
    Author     : Heugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row" >
    <div class="col-xs-12 col-sm-12 col-md-12">
        <form id="form-cad-tiposocorrencia" class="form-horizontal">    
            <input type="hidden" name="id" value="${tiposocorrencia.id}"> 
            
            <div class="form-group">
                <label for="inputDescricao" class="col-sm-2 control-label">Descrição</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="descricao" id="inputDescricao" placeholder="Descrição" value="${tiposocorrencia.descricao}">
                </div>
            </div>
                
                
            <div class="form-group">
                <label for="inputPrazo" class="col-sm-2 control-label">Prazo</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" name="prazo" id="inputPrazo" placeholder="Prazo" value="${tiposocorrencia.prazo}">
                </div>
            </div>
                
            <div id="camposNaoPreenchidos" style="display:none" class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <p id="campoDescricao"></p>
                <p id="campoPrazo"></p>
            </div>
         
        </form>
    </div>        
</div>