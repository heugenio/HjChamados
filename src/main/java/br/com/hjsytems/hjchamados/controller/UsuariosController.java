/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.entity.UnidadesEmpresariais;
import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
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
@RequestMapping("/user")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarios;
    @Autowired
    private UnidadesEmpresariaisRepository unidades;

    @GetMapping
    public ModelAndView Abrir() {
        ModelAndView mv = new ModelAndView("usuarios/manutencao");
        return mv;
    }

    @GetMapping(value = {"/lista", "/lista/{nome}"})
    public ModelAndView listaUsuario(@PathVariable Optional<String> nome) {
        ModelAndView mv = new ModelAndView("usuarios/lista_usuarios");
        String nomePsq = "";
        if (nome.isPresent()) {
            nomePsq = nome.get();
        }

        List<Usuarios> ls = new ArrayList<>();
        ls = usuarios.findByNomeContaining(nomePsq);

        mv.addObject("listaUsuario", ls);

        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("usuarios/form_usuario");

        List<UnidadesEmpresariais> ls = unidades.findAll();
        mv.addObject("listaUnidades", ls);
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("usuarios/form_usuario");
        Usuarios us = usuarios.getOne(id);
        List<UnidadesEmpresariais> ls = unidades.findAll();
        mv.addObject("listaUnidades", ls);
        mv.addObject("user", us);
        return mv;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Usuarios user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } else {
            usuarios.save(user);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
