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
        
        <div id="div_gif" class="gif_event_ajax"></div>
        
        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12">
                
                <form id="form-cad-ocorrencias" class="form-horizontal">    
                    
                    <input type="hidden" name="id" value="${ocorrencia.id}">
                    
                    <div class="form-group">
                        <label for="usuario" class="col-sm-2 control-label">Usuários</label>
                        <div class="col-sm-10">
                            <c:choose>
                                <c:when test="${ocorrencia eq null}">
                                    <input type="hidden" name="usuario" value="${usuario.id}" />
                                    <input type="text" title="Usuário logado"  value="${usuario.nome}" readonly class="form-control" />
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="usuario" value="${ocorrencia.usuario.id}" />
                                    <input type="text" title="Usuário logado"  value="${ocorrencia.usuario.nome}" readonly class="form-control" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="selectUnidade" class="col-sm-2 control-label">Unidades</label>
                        <div class="col-sm-10">
                            <select id="selectUnidade" name="unidadeEmpresarial" class="form-control">
                                <option value="11" disabled selected>Unidades</option>
                                <c:forEach items="${listUnidadeEmpresariais}" var="unidade">
                                    <option value="${unidade.id}" <c:if test="${unidade eq ocorrencia.unidadeEmpresarial}">selected</c:if> > ${unidade.nome}</option>
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
                                    <option value="${fornecedor.id}"<c:if test="${fornecedor eq ocorrencia.fornecedor}">selected</c:if> >${fornecedor.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="selectTipoOcorrenciaDescricao" class="col-sm-2 control-label">Tipo ocorrência</label>
                        <div class="col-sm-10">
                            <select id="selectTipoOcorrenciaDescricao" disabled  name="tiposOcorrencia" class="form-control">
                                <option disabled selected>Tipos ocorrências</option>
                                <c:forEach items="${listTiposOcorrencias}" var="tipoOcorrencia">
                                    <option value="${tipoOcorrencia.id}"<c:if test="${tipoOcorrencia eq ocorrencia.tiposOcorrencia}">selected</c:if> >${tipoOcorrencia.descricao}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="textAreaDescricao" class="col-sm-2 control-label">Descrição</label>
                        <div class="col-sm-10">
                            <textarea rows="8" placeholder="Descreva o ocorrência aqui!" name="descricao" onkeyup="qtdCaracter(this.value)" maxlength="5000" minlength="20" id="textAreaDescricao" class="form-control">${ocorrencia.descricao}</textarea>
                        </div>
                    </div>
                        
                        
                    <div id="camposNaoPreenchidos" style="display:none" class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"><label id="pMsg"></label></span>
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
