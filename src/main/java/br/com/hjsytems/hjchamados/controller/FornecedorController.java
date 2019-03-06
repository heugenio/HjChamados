/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.entity.Fornecedor;
import br.com.hjsytems.hjchamados.entity.UnidadesEmpresariais;
import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.FornecedorRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Heugenio
 */
@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorRepository iFornecedorRepository;
    
    //@Autowired Fornecedor oEFornecedor;
    
    @GetMapping
    public ModelAndView Abrir() {
        return new ModelAndView("fornecedor/manutencao");
    }
    
    @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("fornecedor/form_fornecedor").addObject("listaFornecedores", iFornecedorRepository.findAll());
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } else {
            iFornecedorRepository.save(fornecedor);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping(value = {"/lista", "/lista/{nome}"})
    public ModelAndView listaUsuario(@PathVariable Optional<String> nome) {
        return new ModelAndView("fornecedor/lista_fornecedores").addObject("listaUsuario", iFornecedorRepository.findByNomeContaining(nome.isPresent() ? nome.get():""));
    }
    
}
