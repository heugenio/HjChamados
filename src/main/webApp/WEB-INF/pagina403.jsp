<%-- 
    Document   : pagina403
    Created on : 21/03/2019, 17:46:05
    Author     : Hallef
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />

<html>
    <head>
        <title>SysWeb-Ocorrências | Gerenciador de Ocorrências</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${path}/static/vendor/jquery/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="${path}/static/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <link href="${path}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="shortcut icon" href="favicon.ico" />
    </head>
    <body class="container jumbotron">
        <div>
            <h1 style="text-align: center">Sysweb!</h1>
            <h3 style="text-align: center">Você não possui permissão para acessar esta página!</h3>
        </div>
        <br>
        <p style="text-align: center"><a class="btn btn-primary btn-lg" href="${path}" role="button">Voltar</a></p>
    </body>
</html>


