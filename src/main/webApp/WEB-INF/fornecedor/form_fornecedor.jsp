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

<html>
    <body>
        
        
    <!--    <script>
            $("#form-cad-fornecedor").validate({
            rules: {
                nome: {
                    required: true,
                    minlength: 3
                },
                cnpjCpf: {
                    required: true
                },
                email: {
                    required: true
                },
                telefone: {
                    required: true,
                    minlength: 13
                },
                celular: {
                    required: true,
                    minlength: 14
                }
            },
            messages: {
                nome: {
                    required: "Por favor, informe o nome",
                    minlength: "O nome deve ter pelo menos 3 caracteres"
                },
                cnpjCpf: {
                    required: "É necessário informar CPF/CNPJ"
                },
                email: {
                    required: "É necessário o e-mail"
                },
                telefone: {
                    required: "É necessário informar o Nº telefone",
                    minlength: "Número de telefone inválido"
                },
                celular: {
                    required: "É necessário informar o Nº celular",
                    minlength: "Número de celular inválido"
                }
            }
        });
        </script>-->
    
        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form data-toggle="validator" role="form" id="form-cad-fornecedor" class="form-horizontal">    
                    
                    <input type="hidden" name="id" value="${fornecedor.id}"> 

                    <div class="form-group">
                        <label for="inputNome" class="col-sm-2 control-label">Nome</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nome" id="inputNome" placeholder="Nome" value="${fornecedor.nome}" required >
                        </div>
                    </div>

                    <div class="form-group">

                        <label  class="col-sm-2 control-label">Tipo Pessoa</label>

                        <div class="col-sm-10 ">
                            <label class="toggle">
                                <input onchange="changeCampoCpfCnpj(this.id);" type="radio" id="cpf" name="toggle" checked> <span id="cpfV" class="label-text">CPF</span>
                            </label>
                            <label class="toggle">
                                <input onchange="changeCampoCpfCnpj(this.id);" type="radio" id="cnpj" name="toggle"> <span id="cnpjV" class="label-text">CNPJ</span>
                            </label>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="inputCnpjCpf" class="col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="cnpjCpf" id="inputCnpjCpf" placeholder="CPF" value="${fornecedor.cnpjCpf}" required>
                        </div>
                    </div>      
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email" value="${fornecedor.email}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTelefone" class="col-sm-2 control-label">Telefone</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="telefone" id="inputTelefone" placeholder="Telefone" value="${fornecedor.telefone}" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputCelular" class="col-sm-2 control-label">Celular</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="celular" id="inputCelular" placeholder="Celular" value="${fornecedor.celular}" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputEmailAux" class="col-sm-2 control-label">Email Aux</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="emailAux" id="inputEmailAux" placeholder="E-mail Aux" value="${fornecedor.emailAux}">
                        </div>
                    </div>
                    <div id="camposNaoPreenchidos" style="display:none" class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        <p id="campoNome"></p>
                        <p id="campoCpfCnpj"></p>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>