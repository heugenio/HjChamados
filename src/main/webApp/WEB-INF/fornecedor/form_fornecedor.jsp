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

        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form data-toggle="validator" role="form" id="form-cad-fornecedor" class="form-horizontal">    
                    
                    <input type="hidden" name="id" value="${fornecedor.id}"> 
                    <div class="form-group">
                        <input type="hidden"  name="nome" id="inputNome" >
                        <label for="inputNome" class="col-sm-2 control-label">Nome</label>
                        <div class="col-sm-10" >
                            <select id="usuarioFornecedor" class="form-control" >
                                <c:forEach items="${usuarioFornecedor}" var="usuaforn" >
                                    <option value="${usuaforn.idUsuario}">${usuaforn.nomeUsuario}</option>
                                </c:forEach>
                            </select>
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
                    
                    <div class="form-group">
                        <input id="inputListTipo" type="hidden" name="listTiposOcorrencias" />
                        <label for="inputOcorrencia" class="col-sm-2 control-label">Tipo ocorrência</label>
                        <div class="col-sm-8">
                            <select id="tipoOcorrenciaDescricao" class="form-control">
                                <!--<option selected>Selecione um Tipo</option>-->
                                <c:forEach items="${listTiposOcorrencias}" var="tipoOcorrencia">
                                    <option value="${tipoOcorrencia.id}" >${tipoOcorrencia.descricao}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="button" onclick="popularTabela()" id="btn-addFornTipoOcorrencia" class="btn btn-success">Adicionar</button>   
                        </div>
                    </div>
                        
                    <div id="camposNaoPreenchidos" style="display:none" class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        <p id="campoNome"></p>
                        <p id="campoCpfCnpj"></p>
                    </div>

                    <div >
                        <div>
                            <table id="tbTipoOcorrencia" class="table table-striped table-bordered">
                                <!--Cabecalho da Tabela-->  
                                <thead>
                                    <tr>
                                        <th>Tipo da ocorrência</th>
                                        <th style="text-align: center">Ações</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </div>
       
    </body>
</html>