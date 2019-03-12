<%-- 
    Document   : manutencao
    Created on : 12/03/2019, 14:34:48
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value = "pt_BR"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<jsp:include page="../fragments/cabecallho.jsp"/>

<style>
    #alertStatus {
        margin-bottom: 1px;
        height: 3%;
        line-height:30px;
        padding:0px 15px;
    }
</style>

<div class="container">
    <div class="row"> 
        <div class="panel panel-default">
            
            <div class="panel-heading">
                <div class="col-xs-12 col-sm-12 col-md-6" style="padding-top: 3px;">
                    <strong>Manutenção de Ocorrências</strong> 
                </div> 
                <div class="col-xs-12 col-sm-12 col-md-6">
                    <button id="btn-adcionar" class="btn btn-default pull-right btn-sm">Adicionar</button> 
                </div>                 
                <div class="clearfix"></div>
            </div> 
            
            <div class="panel-body">
                
                <div class="row">
                    
                    <div class="col-xs-5"><!--col-xs-12 col-sm-12 col-md-6 -->
                        <input id="input-fornecedor" placeholder="Fornecedor" class="form-control" type="text"/>
                    </div>  

                    <div class="col-xs-3">
                        <select id="ocorrencia" class="form-control">
                            <option value="" disabled selected>Unidades</option>
                            <c:forEach items="${listaUnidades}" var="ocorrencia">
                                <option value="${ocorrencia.id}" >${ocorrencia.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-xs-3">
                        <div id="alertStatus" class="alert alert-info" role="alert">
                            <label style="color: #000;" class="toggle">
                                <input type="radio" id="aberto" name="toggle"> <span id="aberto" class="label-text">Aberto</span>
                            </label>  
                            <label style="color: #000" class="toggle">
                                <input type="radio" name="toggle"> <span class="label-text">Fechado</span>
                            </label>
                        </div>
                    </div>
                    
                    <div class="col-xs-1">
                        <button id="btn-search" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>                        
                    </div>
                    
                </div> 
                
                <div class="row" id="conteudo"> <!-- Inicio da Grid Fragmento --> 

                </div> 
                
            </div>
        </div>
    </div>
</div>

<jsp:include page="../fragments/rodape.jsp"/> 