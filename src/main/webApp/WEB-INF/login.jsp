<%-- 
    Document   : login
    Created on : 20/03/2019, 10:27:17
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<fmt:setLocale value = "pt_BR"/>
<%--<jsp:include page="fragments/cabecallho.jsp"></jsp:include>--%>
<link href="${path}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style type="text/css">
            body {
                font-family: 'Varela Round', sans-serif;
            }
            .modal-login {
                color: #636363;
                width: 350px;
            }
            .modal-login .modal-content {
                padding: 20px;
                border-radius: 5px;
                border: none;
            }
            .modal-login .modal-header {
                border-bottom: none;
                position: relative;
                justify-content: center;
            }
            .modal-login h4 {
                text-align: center;
                font-size: 26px;
            }
            .modal-login  .form-group {
                position: relative;
            }
            .modal-login i {
                position: absolute;
                left: 13px;
                top: 11px;
                font-size: 18px;
            }
            .modal-login .form-control {
                padding-left: 40px;
            }
            .modal-login .form-control:focus {
                border-color: #00ce81;
            }
            .modal-login .form-control, .modal-login .btn {
                min-height: 40px;
                border-radius: 3px; 
            }
            .modal-login .hint-text {
                text-align: center;
                padding-top: 10px;
            }
            .modal-login .close {
                position: absolute;
                top: -5px;
                right: -5px;
            }
            .modal-login .btn {
                background: #00ce81;
                border: none;
                line-height: normal;
            }
            .modal-login .btn:hover, .modal-login .btn:focus {
                background: #00bf78;
            }
            .modal-login .modal-footer {
                background: #ecf0f1;
                border-color: #dee4e7;
                text-align: center;
                margin: 0 -20px -20px;
                border-radius: 5px;
                font-size: 13px;
                justify-content: center;
            }
            .modal-login .modal-footer a {
                color: #999;
            }
            .trigger-btn {
                display: inline-block;
                margin: 100px auto;
            }
        </style>

    </head>

    <body>
        <!-- Modal HTML -->
        <div id="telaLogin" class="modal fade" data-backdrop="static">
            <div class="modal-dialog modal-login">
                <div class="modal-content">
                    <div class="modal-header">				
                        <h4 class="modal-title">Login</h4>
                        <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="login">
                            <div class="form-group">
                                <i class="glyphicon glyphicon-user"></i>
                                <input type="text" name="user" id="user" class="form-control" placeholder="Username" required="required">
                            </div>
                            <div class="form-group">
                                <i class="glyphicon glyphicon-lock"></i>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="required">					
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary btn-block btn-lg" value="Login">
                            </div>
                        </form>				

                    </div>
                    <div class="modal-footer">
                        <a href="#">Esqueceu a senha?</a>
                    </div>
                </div>
            </div>
        </div>     
    </body>

</html>
<script src="${path}/static/vendor/jquery/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="${path}/static/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $("#telaLogin").modal('show');
    });
</script>
