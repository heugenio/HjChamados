/*
 * Desenvolvido por:    Zeno Dolinski
 * Em:                  15/03/2018
 * Versão:              1.0
 * Finalidade:          Reconstruir a tag SELECT para suprir as necessidades de seleção de grupos e itens 
 * 
 * Utilização:
        Utilize a classe select3 em um objeto select
            ex.:
            <select id="unidades" style="width: 300px;" class="form-control select3" multiple title="Selecione uma unidade">
                <optgroup label="Veículos" value="G1">
                    <option value="12">Volvo</option>
                    <option value="15">Saab 95</option>
                    <option value="23">Mercedes SLK</option>
                    <option value="17">Audi TT</option>
                </optgroup>
                <optgroup label="Frutas" value="G3">
                    <option value="001">Maçã</option>
                    <option value="002">Pera</option>
                    <option value="003">Mamão</option>
                    <option value="004">Goiaba</option>
                    <option value="005">Melancia</option>
                </optgroup>
            </select>
            
        Recuperando os resultados:
            São ao todo 4 funções para recuperar o resultado do select:
                select3SelecionadosValue(objId)
                select3SelecionadosText(objId)
                select3GruposSelecionadosText(objId)
                select3RetornoCompleto(objId)
                
            ex.:
            Supondo-se que no select acima tenham sido selecionados Volvo e Mercedes SLK os resultados serão:
                select3SelecionadosValue("unidades") --> "12;23"
                select3SelecionadosText("unidades") --> "Volvo;Mercedes SLK"
                select3GruposSelecionadosText("unidades") --> "GRUPO VEÍCULOS"
                select3RetornoCompleto("unidades") --> "[{"optgroup":{"value":"G1","text":"Veículos"},"option":[{"value":"12","text":"Volvo"},{"value":"23","text":"Mercedes SLK"}]}]"
            Obs.:
            Com excessão de select3RetornoCompleto, os Resultados serão retornados na forma de string,
            string vazia (""), para nenhum registro selecionado ou uma string contendo um ou mais registros
            selecionados, saparados por ponto e vírgula (;).
            Caso houver necessidade de se trabalhar com o resultado na forma de Array, utilize a função resultado.split(";")
            
            O select3RetornoCompleto retornará um array com os grupos (optgroup) e um array dos itens (option) selecionados em cada grupo
            
            var resultado = select3RetornoCompleto("unidades");
            resultado = [
                            {
                                optgroup: {value: "G1", text: "Veículos"},
                                option: [
                                    {value: "12", text: "Volvo"},
                                    {value: "23", text: "Mercedes SLK"}
                                ]
                            },
                            {
                                optgroup: {value: "G3", text: "Frutas"},
                                option: [
                                    {value: "001", text: "Maçã"},
                                    {value: "002", text: "Pera"},
                                    {value: "003", text: "Mamão"}
                                ]
                            }
                        ];
*/



var expandido = [];
var tituloOriginal = [];

$(document).ready(function () {
    var elementosZ = document.getElementsByClassName("select3");
    while(elementosZ.length > 0){
        constroeSelect(elementosZ[0]);
    }
});

function initSelect3(){
    var elementosZ = document.getElementsByClassName("select3");
    while(elementosZ.length > 0){
        constroeSelect(elementosZ[0]);
    }
}
	
//teste
function constroeSelect(obj){
    html = '<div id="' + obj.id + '" class="multiselect">\n';
    html += '	<div class="selectBox">\n';
    html += '	  <select id="objSelect" class="form-control" tabindex="-1">\n';
    html += '		<option id="titulo">' + obj.getAttribute("title") + '</option>\n';
    html += '	  </select>\n';
    html += '   <div id="overSelect" class="overSelect"></div>\n';
    html += '	</div>\n';
    html += '	<div id="listagem">\n';
    html += '		<div class="barraBotoes">\n';
    html += '			<div class="todos" id="todos">Selecionar todos</div>\n';
    html += '			<div class="todos" id="togle">Inverter seleção</div>\n';
    html += '		</div>\n';
    html += '		<div id="checkboxes" tabindex="none">\n';

    var group = '';
    for(var i=0; i<obj.length; i++){
        var selected = '';
        if(group !== (obj[i].parentNode.label || "")){
            html += '			<div>\n';
            html += '				<label style="display: block;" class="grupo" class="grupo" title="Marca ou desmarca os itens deste grupo" data-text="' + obj[i].parentNode.label + '" data-value="' + obj[i].parentNode.getAttribute('value') + '">' + obj[i].parentNode.label + '</label>\n';
            group = obj[i].parentNode.label || "";
        }
        if(obj[i].selected){
            selected = 'checked=""';
        }
        html += '				<label style="display: block; margin-left: 5px;" class="item" for="' + obj.id + '_' + obj[i].value + '_Z"><input style="display: inline;" data-value="' + obj[i].value + '" data-text="' + obj[i].text + '" data-grupo="' + group + '" type="checkbox" id="' + obj.id + '_' + obj[i].value + '_Z" ' + selected + ' /><div style="display: inline;" class="txtItem">' + obj[i].text + '</div></label>\n';
        if( obj[i+1] === undefined || (obj[i].parentNode.label || "") !== (obj[i+1].parentNode.label || "") ){
            html += '			</div>\n';
        }
    }

    html += '		</div>\n';
    html += '	</div>\n';
    html += '</div>\n';

    obj.parentNode.innerHTML = html;
    select3Inicializar(obj.id);
}

window.onresize = function(event) {
    atualizarLargura();
};

function atualizarLargura(){
    itens = document.getElementsByClassName("multiselect");
    for(var i=0; i<itens.length; i++){
        w = itens[i].offsetWidth;
        itens[i].querySelector("#listagem").style.width = w + "px";
    }	
}

function select3Inicializar(objId){
    var objMain = document.getElementById(objId);
    if(objMain===null){ alert("O objeto " + objId + ", não foi encontrado!"); return false;}
    expandido.push(objId);
    expandido[objId] = false;
    expandido.push(objId);
    tituloOriginal[objId] = objMain.querySelector("#titulo").innerHTML;

    document.addEventListener('keyup',  function(e){verificaFocus(e, objMain);}, false);
    document.addEventListener('mouseup', function(e){verificaFocus(e, objMain);}, false);
    objMain.querySelector("#objSelect").addEventListener('click', function(e){verificaFocus(e, objMain);}, false);

    objMain.querySelector("#todos").addEventListener('click', function(){selecionaTodos(objMain);}, false);
    objMain.querySelector("#togle").addEventListener('click', function(){togleTodos(objMain);}, false);

    w = objMain.offsetWidth;
    objMain.querySelector("#listagem").style.width = w + "px";
    objMain.querySelector("#listagem").style.position = "absolute";
    objMain.querySelector("#listagem").style.zIndex = "1000";

    var input = objMain.querySelector("#listagem");
    var itens = input.getElementsByTagName("input");
    for(var i=0; i<itens.length; i++){
        itens[i].parentElement.setAttribute("for", objId + "_" + i + "_" + itens[i].id);
        itens[i].id = objId + "_" + i + "_" + itens[i].id;
        itens[i].addEventListener('change', function(){select3SomaItens(objMain);}, false);
    }
    itens = input.getElementsByClassName("grupo");
    for(var i=0; i<itens.length; i++){
        itens[i].id = objId + "_" + i + "_" + itens[i].id;
        itens[i].addEventListener("click", function(){
            selecionaGrupo(this, objMain);
        }, false);
    }
    select3SomaItens(objMain);
}

function showCheckboxes(objMain) {
    var listagem = objMain.querySelector("#listagem");
    var objAtivo = objMain.querySelector("#objSelect");
    if (!expandido[objMain.id]) {
        atualizarLargura();
        listagem.style.display = "block";
        expandido[objMain.id] = true;
        objAtivo.style.boxShadow = "0px 0px 5px #5ea9e4";
        objAtivo.style.border = "1px solid #5ea9e4";
    } else {
        listagem.style.display = "none";
        expandido[objMain.id] = false;
        objAtivo.style.boxShadow = "inset 0 1px 1px rgba(0,0,0,.075)";
        objAtivo.style.border = "1px solid #ccc";
    }
}

function verificaFocus(e, objMain){
    e = e || window.event;
    var elements = objMain.querySelector("#listagem").getElementsByTagName('*');
    var isFocused = false;
    for(var i=0; i<elements.length; i++){
        if(e.target === elements[i]){
            isFocused = true;
            break;
        }
    }
    if(e.target===objMain.querySelector("#overSelect")){
        showCheckboxes(objMain);
    } else {
        expandido[objMain.id] = false;
        if(!isFocused) expandido[objMain.id] = true;
        showCheckboxes(objMain);
    }
}
	
function select3SelecionadosValue(objId){
    var objSel = document.getElementById(objId);
    var boxSel = objSel.querySelector("#listagem");
    var itensSel = boxSel.querySelectorAll("input");
    var result = [];
    
    for(var i=0; i<itensSel.length; i++){
        if(itensSel[i].checked){
            result.push(itensSel[i].dataset.value);
        }
    }
    
    return result;
}

function select3SelecionadosText(objId){
    var objSel = document.getElementById(objId);
    var boxSel = objSel.querySelector("#listagem");
    var itensSel = boxSel.querySelectorAll("input");
    var result = "";
    for(var i=0; i<itensSel.length; i++){
        if(itensSel[i].checked){
            if(result !== "") result += ";";
            result += itensSel[i].dataset.text;
        }
    }
    return result;
}

function select3GruposSelecionadosText(objId){
    
    var objSel = document.getElementById(objId);
    var grupos = objSel.querySelectorAll(".grupo");
    ///var boxSel = objSel.querySelector("#listagem");
    //var itensSel = boxSel.querySelectorAll("input");
    var result = "";
    for(var j=0; j<grupos.length; j++){
        var itensSel = grupos[j].parentNode.querySelectorAll("input");
        for(var i=0; i<itensSel.length; i++){
            if(itensSel[i].checked){
                if(result !== "") result += ";";
                result += grupos[j].textContent;
                break;
            }
        }
    }
    return result;
}

function select3RetornoArray(objId){
    var objSel = document.getElementById(objId);
    var boxSel = objSel.querySelector("#listagem");
    var itensSel = boxSel.querySelectorAll("input");
    var resultado = [];
    for(var i=0; i<itensSel.length; i++){
        if(itensSel[i].checked){
            resultado.push(converteMonetarioToNumero(itensSel[i].dataset.value));
        }
    }
    return resultado;
}

function select3RetornoCompleto(objId){
    var objSel = document.getElementById(objId);
    var boxSel = objSel.querySelector("#listagem");
    var itensSel = boxSel.querySelectorAll("input");
    var resultado = [];
    var g = "_";
    var itensGrupo = [];
    for(var i=0; i<itensSel.length; i++){
        if(itensSel[i].checked){
            var oGrupo = {};
            oGrupo.dataset = {value: "", text: ""};
            if(itensSel[i].parentNode.parentNode.querySelector(".grupo")){
                oGrupo.dataset = {value: itensSel[i].parentNode.parentNode.querySelector(".grupo").dataset.value, text: itensSel[i].parentNode.parentNode.querySelector(".grupo").dataset.text};
                if(oGrupo.dataset.value==="null") oGrupo.dataset.value = "";
                if(oGrupo.dataset.text==="null") oGrupo.dataset.text = "";
            }
            if(g !== oGrupo.dataset.text){
                var res = {};
                res.optgroup = {};
                res.optgroup.value = oGrupo.dataset.value;
                res.optgroup.text = oGrupo.dataset.text;
                itensGrupo = res.option = [];
                g = oGrupo.dataset.text;
                resultado.push(res);
            }
            var item = {};
            item.value = itensSel[i].dataset.value;
            item.text = itensSel[i].dataset.text;
            itensGrupo.push(item);
        }
    }
    return resultado;
}


function select3V2(objId){
    
}

function select3SomaItens(objMain){
    var input = objMain.querySelector("#listagem");
    var itens = input.getElementsByTagName("input");
    var resultado = 0;
    var ck =  true;
    for(var i=0; i<itens.length; i++){
        if(itens[i].checked){
            resultado++;
            itens[i].parentNode.className += " selecionado"; 
        }else{
            itens[i].parentNode.className = itens[i].parentNode.className.replace ( /(?:^|\s)selecionado(?!\S)/g , '' );
        }
        ck = ck && itens[i].checked;
    }
    var titulo = objMain.querySelector("#titulo");
    if(resultado===0) titulo.innerHTML = tituloOriginal[objMain.id];
    if(resultado===1) titulo.innerHTML = "[1] item selecionado";
    if(resultado > 1) titulo.innerHTML = "[" + resultado + "] itens selecionados";

    var msg = "Remover seleção";
    if(ck === false) msg = "Selecionar todos"; 
    objMain.querySelector("#todos").innerHTML = msg;
    select3V2(objMain.id);
}

function selecionaGrupo(obj, objMain){
    var itens = obj.parentElement.getElementsByTagName("input");
    var togle = true;
    var base = itens[0].checked;
    for(var i=1; i<itens.length; i++){
        if(itens[i].checked !== base){
            togle = false;
            break;
        }
    }
    for(var i=0; i<itens.length; i++){
        ck = true;
        if(togle) ck = ! base;
        itens[i].checked = ck;
    }
    expandido[objMain.id] = false;
    select3SomaItens(objMain);
}

function selecionaTodos(objMain){
    var itens = objMain.querySelector("#listagem").getElementsByTagName("input");
    var togle = true;
    var base = itens[0].checked;
    for(var i=1; i<itens.length; i++){
        if(itens[i].checked !== base){
            togle = false;
            break;
        }
    }
    var ck = true;
    for(var i=0; i<itens.length; i++){
        if(togle) ck = ! base;
        itens[i].checked = ck;
    }
    expandido[objMain.id] = false;
    select3SomaItens(objMain);
}

function togleTodos(objMain){
    var itens = objMain.querySelector("#listagem").getElementsByTagName("input");
    for(var i=0; i<itens.length; i++){
        itens[i].checked = !itens[i].checked;
    }
    expandido[objMain.id] = false;
    select3SomaItens(objMain);
}