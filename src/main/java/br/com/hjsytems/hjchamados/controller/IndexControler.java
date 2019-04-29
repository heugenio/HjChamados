package br.com.hjsytems.hjchamados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Heugenio
 */
@Controller
@RequestMapping("/")
public class IndexControler {
    
    @GetMapping
    public String abrir() {
       return "redirect:/ocorrencias";
       // return new ModelAndView("index");
    }
}
