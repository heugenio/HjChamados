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
@RequestMapping("/tpoc")
public class tiposOcorrenciaController {

    @Autowired
    private TiposOcorrenciaRepository tiposocorrencia;
    

    @GetMapping
    public ModelAndView Abrir() {
        ModelAndView mv = new ModelAndView("tiposocorrencia/manutencao");
        return mv;
    }
    
    @GetMapping(value = {"/lista", "/lista/{descricao}"})
    public ModelAndView listaTiposOcorrencia(@PathVariable Optional<String> descricao) {
        ModelAndView mv = new ModelAndView("tiposocorrencia/lista_tiposocorrencia");
        String descricaoPsq = "";
        if (descricao.isPresent()) {
            descricaoPsq = descricao.get();
        }
        
        List<TiposOcorrencia> ls = new ArrayList<>();
        ls = tiposocorrencia.findBydescricaoContaining(descricaoPsq);

        mv.addObject("listaTiposOcorrencia", ls);

        return mv;
    }    

   @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("tiposocorrencia/form_tiposocorrencia");

        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("tiposocorrencia/form_tiposocorrencia");
        TiposOcorrencia tpo = tiposocorrencia.getOne(id);

        mv.addObject("tiposocorrencia", tpo);
        return mv;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute TiposOcorrencia tiposocorrencia, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } else {
            this.tiposocorrencia.save(tiposocorrencia);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}

