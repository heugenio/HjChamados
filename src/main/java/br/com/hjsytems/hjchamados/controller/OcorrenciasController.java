
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hallef
 */
@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciasController {
    
    @Autowired private UnidadesEmpresariaisRepository iEmpresariaisRepository;
    
    @GetMapping
    public ModelAndView preparaEstadoInicial() {
        return new ModelAndView("ocorrencias/manutencao").addObject("listaUnidades", iEmpresariaisRepository.findAll());
    }
    
    
    
}
