<%-- 
    Document   : lista_fornecedores
    Created on : 07/03/2019, 13:14:56
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
                        <th>Nome</th>
                        <th>CPF/CNPJ</th>
                        <th>Email</th>
                        <th>Telefone</th>
                        <th>Celular</th>
                        <th>Email Aux</th>
                        <th style="text-align: center">Ações</th>
                    </tr>
                </thead>
                <!--Fim Cabecalho da Tabela-->  
                <tbody>
                    <!--c:forEach laco de repeticao-->
                    <c:forEach items="${listaFornecedor}" var="fornecedores"> 
                        <!--tr Linha-->
                        <tr> 
                            <!--td coluna-->
                            <td> ${fornecedores.nome}</td> 
                            <td> ${fornecedores.cnpjCpf}</td>
                            <td> ${fornecedores.email}</td>
                            <td> ${fornecedores.telefone}</td>
                            <td> ${fornecedores.celular}</td>
                            <td> ${fornecedores.emailAux}</td>
                            <td style="text-align: center"> 
                                <a href="#" data-toggle="tooltip" title="Clique para editar" onclick="alteraFornecedor(${fornecedores.id},'${fornecedores.nome}')">
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