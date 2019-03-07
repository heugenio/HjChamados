<%-- 
    Document   : rodape
    Created on : 08/02/2019, 14:52:53
    Author     : Heugenio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<fmt:setLocale value = "pt_BR"/>



<html>
    <body>
      <!-- Inserimos o rodapé -->  
        <div id="footer" style="position: fixed; bottom: 0;">
            <div class="container">
                <p class=" credit" style="font-size: 10px;">Desenvolvido por &copy; <a href="http://hjsystems.com.br">HJ-Systems </a>| Automação Comercial</p>
            </div>
        </div>

    </body>
    <script src="${path}/static/vendor/jquery/jquery-3.1.1.min.js"></script>
    <script src="${path}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${path}/static/vendor/jquery_mask/jquery.mask.min.js"></script>
    <script src="${path}/static/vendor/jquery_mask/jquery.mask.js"></script>
    <script src="${path}/static/vendor/jquery_validator/jquery.validate.min.js" ></script>
</html>