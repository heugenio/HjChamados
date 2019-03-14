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

    <div class="col-xs-12 col-sm-12 col-md-12">
        <div style="margin-top: 9px" class="table-responsive" >
            <table class="table table-bordered table-condensed table-hover">
                <!--Cabecalho da Tabela-->  
                <thead>
                    <tr>
                        <th>Nome usuário</th>
                        <th>Data abertura</th>
                        <th>Data fechamento</th>
                        <th>Status</th>
                        <th>Fornecedor</th>
                        <th>Tipo da ocorrência</th>
                        <th>Unidade</th>
                        <th style="text-align: center">Ações</th>
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
                            <td> <fmt:formatDate value="${ocorrencias.dataFechamento}" pattern="dd/MM/yyyy"/></td>
                            <td> ${ocorrencias.status}</td>
                            <td> ${ocorrencias.fornecedor.nome}</td>
                            <td> ${ocorrencias.tiposOcorrencia.descricao}</td>
                            <td> ${ocorrencias.unidadeEmpresarial.nome}</td>
                            <td style="text-align: center"> 
                                <a href="#" data-toggle="tooltip" title="Clique para editar" onclick="alterarOcorrencia(${ocorrencias.id})">
                                    <i class="glyphicon glyphicon-pencil"></i>                                 
                                </a>
                            </td>  
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</html>
