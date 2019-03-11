<%-- 
    Document   : cabecallho
    Created on : 08/02/2019, 14:52:45
    Author     : Heugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<fmt:setLocale value = "pt_BR"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SysWeb-Ocorrências | Gerenciador de Ocorrências</title>
        <link rel="shortcut icon" href="favicon.ico" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="${path}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="${path}/static/css/style.css" rel="stylesheet" type="text/css"/>   
        <link href="${path}/static/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="${path}/static/css/radio_cpf_cnpj.css" rel="stylesheet" type="text/css"/>
        <link href="${path}/static/vendor/data-tables/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                        <span class="sr-only">Alternar Navegação</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="index.jsp"><img class="logo" src="${path}/static/imagens/sys200.png" width="100" alt="SysWeb Conferência"></a>
                </div>
                <div class="collapse navbar-collapse js-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown mega-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ocorrências<span class="caret"></span></a>	
                            <ul class="dropdown-menu mega-dropdown-menu">
                                <li class="col-sm-3">
                                    <ul>
                                        <li class="dropdown-header">Ocorrências</li>
                                        <li><a href="Ocorrencias.jsp">Ocorrências</a></li>
                                    </ul>
                                </li>
                                <li class="col-sm-3">
                                    <ul>
                                        <li class="dropdown-header">Cadastros</li>
                                        <li><a href="${path}/user">Usuarios</a></li>
                                        <li><a href="${path}/tpoc">Tipos Ocorrência</a></li>
                                        <li><a href="${path}/fornecedor">Fornecedores</a></li>
                                    </ul>
                                </li>
                            </ul>	
                        </li>
                        <li><a href="ServSair">Sair</a></li>
                    </ul>	
                </div><!-- /.nav-collapse -->
            </nav>
        </div> 