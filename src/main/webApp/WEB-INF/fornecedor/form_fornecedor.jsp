<%-- 
    Document   : form_fornecedor
    Created on : 06/03/2019, 13:25:06
    Author     : Hallef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row" >
    <div class="col-xs-12 col-sm-12 col-md-12">
        <form id="form-cad-fornecedor" class="form-horizontal">    
            <input type="hidden" name="id" value="${fornecedor.id}"> 

            <div class="form-group">
                <label for="inputNome" class="col-sm-2 control-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="nome" id="inputNome" placeholder="Nome" value="${fornecedor.nome}">
                </div>
            </div>

            <div class="form-group">
                
                <label  class="col-sm-2 control-label">Tipo Pessoa</label>

                <div class="col-sm-10 ">
                    <label class="toggle">
                        <input onchange="changeCampoCpfCnpj(this.id);" type="radio" id="cpf" name="toggle" checked> <span class="label-text">CPF</span>
                    </label>
                    <label class="toggle">
                        <input onchange="changeCampoCpfCnpj(this.id);" type="radio" id="cnpj" name="toggle"> <span class="label-text">CNPJ</span>
                    </label>
                </div>

            </div>

            <div class="form-group">
                <label for="inputCnpjCpf" class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="cnpjCpf" id="inputCnpjCpf" placeholder="CPF" value="${fornecedor.cnpjCpf}">
                </div>
            </div>      
            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" value="${fornecedor.email}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputTelefone" class="col-sm-2 control-label">Telefone</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="telefone" id="inputTelefone" placeholder="Telefone" value="${fornecedor.telefone}">
                </div>
            </div>

            <div class="form-group">
                <label for="inputCelular" class="col-sm-2 control-label">Celular</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="celular" id="inputCelular" placeholder="Celular" value="${fornecedor.celular}">
                </div>
            </div>

            <div class="form-group">
                <label for="inputEmailAux" class="col-sm-2 control-label">Email Aux</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="emailAux" id="inputEmailAux" placeholder="E-mail Aux" value="${fornecedor.emailAux}">
                </div>
            </div>

        </form>
    </div>        
</div>    
