/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
public class UsuariosController {

    @Autowired private UsuarioRepository usuarios;
    @Autowired private UnidadesEmpresariaisRepository unidades;

    @GetMapping
    public ModelAndView abrir() {
        return new ModelAndView("usuarios/manutencao");
    }

    @GetMapping(value = {"/lista", "/lista/{nome}"})
    public ModelAndView listaUsuario(@PathVariable Optional<String> nome) {
        String nomePsq = "";
        if (nome.isPresent()) nomePsq = nome.get();
        return new ModelAndView("usuarios/lista_usuarios").addObject("listaUsuario", usuarios.findByNomeContaining(nomePsq));
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("usuarios/form_usuario").addObject("listaUnidades", unidades.findAll());
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        return new ModelAndView("usuarios/form_usuario")
                  .addObject("usuario", usuarios.getOne(id))
                  .addObject("listaUnidades", unidades.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Usuarios user, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        usuarios.save(user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
