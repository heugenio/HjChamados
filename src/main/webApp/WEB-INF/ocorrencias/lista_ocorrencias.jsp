<%-- 
    Document   : lista_ocorrencias
    Created on : 12/03/2019, 14:35:29
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    
    <script>
        $(document).ready(function() {
          $('[data-toggle="tooltip"]').tooltip(); 
        });
    </script>
    
    <c:set var = "status" value = "Aberto"/>

    <div class="col-xs-12 col-sm-12 col-md-12">
        <div style="margin-top: 9px" class="table-responsive" >
            <table id="tbOcorrencias" class="table table-bordered table-condensed table-hover">
                <!--Cabecalho da Tabela-->  
                <thead>
                    <tr>
                        <th>Nome usuário</th>
                        <th>Data abertura</th>
                        <th style="display: none;" id="dataFechamento">Data fechamento</th>
                        <th>Status</th>
                        <th>Fornecedor</th>
                        <th>Tipo da ocorrência</th>
                        <th>Unidade</th>
                        <th style="text-align: center">Ações</th>
                        <th style="text-align: center">Finalizar ocorrência</th>
                    </tr>
                </thead>
                <!--Fim Cabecalho da Tabela-->  
                <tbody>
                    <!--c:forEach laco de repeticao-->
                    <c:forEach items="${listaOcorrencias}" var="ocorrencias"> 
                        <!--tr Linha-->
                        <tr> 
                            <!--td coluna-->
                            
                            <td> ${ocorrencias.usuario.nome}</td> 
                            <td> <fmt:formatDate value="${ocorrencias.dataAbertura}" pattern="dd/MM/yyyy"/></td>
                            
                            <c:if test="${ocorrencias.dataFechamento ne null}">
                                <td> <fmt:formatDate value="${ocorrencias.dataFechamento}" pattern="dd/MM/yyyy"/></td>
                            </c:if>        
                            <c:choose>
                                <c:when test = "${status == ocorrencias.status}">
                                    <td><span class="label label-success">${ocorrencias.status}</span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="label label-danger">${ocorrencias.status}</span></td>
                                </c:otherwise>
                            </c:choose>
                                
                            <td id="fornNome"> ${ocorrencias.fornecedor.nome}</td>
                            <td> ${ocorrencias.tiposOcorrencia.descricao}</td>
                            <td id="uniNome"> ${ocorrencias.unidadeEmpresarial.nome}</td>
                            <td style="text-align: center">
                                <c:choose>
                                    <c:when test = "${status == ocorrencias.status}" >
                                        <a href="#" data-toggle="tooltip" title="Editar" onclick="alterarOcorrencia(${ocorrencias.id})">
                                            <i class="glyphicon glyphicon-pencil"></i>                                 
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a data-toggle="tooltip" title="Não é possível editar"> <%--onclick="alterarOcorrencia(${ocorrencias.id})"--%>
                                            <i class="glyphicon glyphicon-ban-circle"></i>                                 
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            
                            <td style="text-align: center">
                                <c:choose>
                                    <c:when test="${status eq ocorrencias.status}">
                                        <a href="#" onclick="alterarStatus(${ocorrencias.id},'${ocorrencias.status}')" data-toggle="tooltip" title="Finalizar ocorrência"> <%--${ocorrencias.id},'${ocorrencias.status}'--%>
                                            <i class="glyphicon glyphicon-retweet"></i>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="disabled" data-toggle="tooltip" title="Não é possível alterar o status"> <%-- onclick="alterarStatus(${ocorrencias.id},'${ocorrencias.status}')"--%>
                                            <i class="glyphicon glyphicon-ban-circle"></i>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>  
</html>
