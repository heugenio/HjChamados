<%-- 
    Document   : lista_tiposocorrencia
    Created on : 28/02/2019, 12:00:28
    Author     : Heugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    <th>Descrição</th>
                    <th>Prazo</th>
                    <th style="text-align: center">Ações</th>
                </tr>
            </thead>
            <!--Fim Cabecalho da Tabela-->  
            <tbody>
                <!--c:forEach laco de repeticao-->
                <c:forEach items="${listaTiposOcorrencia}" var="tiposOcorrencia"> 
                    <!--tr Linha-->
                    <tr> 
                        <!--td coluna-->
                        <td> ${tiposOcorrencia.descricao}</td>
                        <td> ${tiposOcorrencia.prazo}</td>
                        <td style="text-align: center"> 
                            <a href="#" data-toggle="tooltip" title="Clique para editar" onclick="alteraTiposOcorrencia(${tiposOcorrencia.id})">
                                <i class="glyphicon glyphicon-pencil"></i>                                 
                            </a>
                        </td>  
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>