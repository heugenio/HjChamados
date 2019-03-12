/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import java.util.List;
import br.com.hjsytems.hjchamados.repository.TiposOcorrenciaRepository;
import java.util.ArrayList;
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
@RequestMapping("/tiposocorrencia")
public class tiposOcorrenciaController {

    @Autowired private TiposOcorrenciaRepository tiposocorrencia;
    

    @GetMapping
    public ModelAndView abrir() {
        return new ModelAndView("tiposocorrencia/manutencao");
    }
    
    @GetMapping(value = {"/lista", "/lista/{descricao}"})
    public ModelAndView listaTiposOcorrencia(@PathVariable Optional<String> descricao) {
        return new ModelAndView("tiposocorrencia/lista_tiposocorrencia")
                .addObject("listaTiposOcorrencia", tiposocorrencia.findBydescricaoContaining(descricao.isPresent() == true ? descricao.get() : ""));
    }    

   @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("tiposocorrencia/form_tiposocorrencia");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        return new ModelAndView("tiposocorrencia/form_tiposocorrencia")
                .addObject("tiposocorrencia", tiposocorrencia.getOne(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute TiposOcorrencia tiposocorrencia, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        this.tiposocorrencia.save(tiposocorrencia);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}

