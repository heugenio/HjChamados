<%-- 
    Document   : lista_usuarios
    Created on : 27/02/2019, 19:16:52
    Author     : Heugenio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

<div class="col-xs-12 col-sm-12 col-md-12">
    <div class=" mt-20">
        <table class="table table-bordered table-condensed table-hover" id="tabelaUsuarios" width="100%">
            <!--Cabecalho da Tabela-->  
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th style="text-align: center">Ações</th>
                </tr>
            </thead>
            <!--Fim Cabecalho da Tabela-->  
            <tbody>
                <!--c:forEach laco de repeticao-->
                <c:forEach items="${listaUsuario}" var="user"> 
                    <!--tr Linha-->
                    <tr> 
                        <!--td coluna-->
                        <td> ${user.nome}</td> 
                        <td> ${user.login}</td>
                        <td> ${user.email}</td>
                        <td style="text-align: center"> 
                            <a href="#" data-toggle="tooltip" title="Clique para editar" onclick="alteraUser(${user.id})">
                                <i class="glyphicon glyphicon-pencil"></i>                                 
                            </a>
                        </td>  
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>