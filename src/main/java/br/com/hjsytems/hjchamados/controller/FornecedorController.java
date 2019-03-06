/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Heugenio
 */
@Controller
@RequestMapping("/frnc")
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedores;
    
    
}
