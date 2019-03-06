<%-- 
    Document   : form_usuario
    Created on : 27/02/2019, 20:21:08
    Author     : Heugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row" >
    <div class="col-xs-12 col-sm-12 col-md-12">
        <form id="form-cad-user" class="form-horizontal">    
            <input type="hidden" name="id" value="${user.id}"> 
            
            <div class="form-group">
                <label for="inputNome" class="col-sm-2 control-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="nome" id="inputNome" placeholder="Nome" value="${user.nome}">
                </div>
            </div>    
            <div class="form-group">
                <label for="inputLogin" class="col-sm-2 control-label">Login</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="login" id="inputLogin" placeholder="Login" value="${user.login}">
                </div>
            </div>      
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" name="email" id="inputEmail3" placeholder="Email" value="${user.email}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="senha" id="inputPassword3" placeholder="Password" value="${user.senha}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputLoja" class="col-sm-2 control-label">Loja</label>
                <div class="col-sm-10">
                    <select class="form-control" name="unidadeEmpresarial">
                        <c:forEach items="${listaUnidades}" var="unidade">
                            <option value="${unidade.id}" <c:if test="${user.unidadeEmpresarial eq unidade}"> selected="" </c:if>  > ${unidade.nome} </option>                            
                        </c:forEach>

                    </select>
                </div>
            </div>    


        </form>
    </div>        
</div>    