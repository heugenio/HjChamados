
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.entity.Grupo;
import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.GrupoRepository;
import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
import java.util.LinkedList;
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
@RequestMapping("/usuario")
public class UsuariosController {

    @Autowired private UsuarioRepository usuarios;
    @Autowired private UnidadesEmpresariaisRepository unidades;
    @Autowired private GrupoRepository iGrupoRepository;

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
        return new ModelAndView("usuarios/form_usuario")
                .addObject("listaUnidades", unidades.findAll())
                .addObject("listGrupos",iGrupoRepository.findAll());
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        return new ModelAndView("usuarios/form_usuario")
                  .addObject("usuario", usuarios.getOne(id))
                  .addObject("listaUnidades", unidades.findAll());
    }

    @PostMapping("/salvar/{id}")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Usuarios oEUsuarios,@PathVariable Integer[] id, BindingResult bindingResult) {
        
        List<Grupo> grupos = new LinkedList<>();
        Grupo oEGrupo;
        
        for (Integer ids : id) {
            oEGrupo = new Grupo();
            oEGrupo.setCodigo(Long.valueOf(ids));
            grupos.add(oEGrupo);
        }
        
        oEUsuarios.setGrupos(grupos);
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        
        usuarios.save(oEUsuarios);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
