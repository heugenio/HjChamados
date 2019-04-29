
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsystems.hjchamados.dao.ConsultaEntidadeDao;
import br.com.hjsystems.hjchamados.util.SITUACAO;
import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import br.com.hjsytems.hjchamados.repository.TiposOcorrenciaRepository;
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

    @Autowired private TiposOcorrenciaRepository iTiposOcorrenciaRepository;
    @Autowired private ConsultaEntidadeDao dao;

    @GetMapping
    public ModelAndView abrir() {
        return new ModelAndView("tiposocorrencia/manutencao");
    }
    
    @GetMapping(value = {"/lista", "/lista/{descricao}"})
    public ModelAndView listaTiposOcorrencia(@PathVariable Optional<String> descricao) {
        return new ModelAndView("tiposocorrencia/lista_tiposocorrencia")
                .addObject("listaTiposOcorrencia", iTiposOcorrenciaRepository.findBydescricaoContaining(descricao.isPresent() == true ? descricao.get() : ""));
    }    

   @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("tiposocorrencia/form_tiposocorrencia");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {
        return new ModelAndView("tiposocorrencia/form_tiposocorrencia")
                .addObject("tiposocorrencia", iTiposOcorrenciaRepository.getOne(id));
    }

    @PostMapping("/salvar")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute TiposOcorrencia tiposocorrencia, BindingResult bindingResult) {
        String msgRetorno = "";
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        try {
            if(dao.consultaEntidade(tiposocorrencia) == SITUACAO.NOVO_REGISTRO) {
                iTiposOcorrenciaRepository.save(tiposocorrencia);
            } else if(dao.consultaEntidade(tiposocorrencia) == SITUACAO.MESMO_REGISTRO) {
                msgRetorno = "Este Tipo de Ocorrência ja foi cadastrada!";
            } else if(dao.consultaEntidade(tiposocorrencia) == SITUACAO.SEM_ALTERACAO){
                msgRetorno = "Operação inválida!";
            }
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(msgRetorno, HttpStatus.OK);
    }
}

