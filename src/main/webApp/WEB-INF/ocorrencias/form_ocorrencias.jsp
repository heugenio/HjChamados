<%-- 
    Document   : form_ocorrencias
    Created on : 12/03/2019, 14:35:12
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    
    <body>
        
        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12">
                
                <form data-toggle="validator" role="form" id="form-cad-ocorrencias" class="form-horizontal">    
                    
                    <input type="hidden" name="id" value="${ocorrencias.id}">
                    
                    <div class="form-group">
                        <label for="usuario" class="col-sm-2 control-label">Usuários</label>
                        <div class="col-sm-10">
                            <input type="text" title="Usuário logado" id="usuario"  class="form-control" readonly="false" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="selectUnidade" class="col-sm-2 control-label">Unidades</label>
                        <div class="col-sm-10">
                            <select id="selectUnidade" name="unidadeEmpresarial" class="form-control">
                                <option value="11" disabled selected>Unidades</option>
                                <c:forEach items="${listUnidadeEmpresariais}" var="unidades">
                                    <option value="${unidades.id}" >${unidades.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                          
                    <div class="form-group">
                        <label for="selectFornecedor"  class="col-sm-2 control-label">Fornecedor</label>
                        <div class="col-sm-10">
                            <select id="selectFornecedor" onchange="changeSelects(this.id)" name="fornecedor" class="form-control">
                                <option value="11" disabled selected>Fornecedores</option>
                                <c:forEach items="${listFornecedores}" var="fornecedor">
                                    <option value="${fornecedor.id}" >${fornecedor.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="selectTipoOcorrenciaDescricao" class="col-sm-2 control-label">Tipo ocorrência</label>
                        <div class="col-sm-10">
                            <select id="selectTipoOcorrenciaDescricao" disabled  name="tiposOcorrencia" class="form-control">
                                <option disabled selected>Tipos ocorrências</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="textAreaDescricao" class="col-sm-2 control-label">Descrição</label>
                        <div class="col-sm-10">
                            <textarea value="${ocorrencias.descricao}" rows="8" placeholder="Descreva o ocorrência aqui!" name="descricao" onkeyup="qtdCaracter(this.value)" id="textAreaDescricao" class="form-control"></textarea>
                        </div>
                    </div>
                        
                        
                    <div id="camposNaoPreenchidos" style="display:none" class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        <p id="pMsg"></p>
                    </div>
                        
                    <div class="form-group">
                        <div id="dvCaracterRestante" style="display: none; margin-bottom: 1px;height: 3%;line-height:18px; padding:0px 10px;" class="alert alert-warning col-sm-3">
                            <p id="caracterRestante"></p>
                        </div>
                    </div>
                        
                </form>
                    
            </div>
        </div>
        
    </body>
    
</html>
