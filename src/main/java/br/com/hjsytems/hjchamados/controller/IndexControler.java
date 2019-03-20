package br.com.hjsytems.hjchamados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Heugenio
 */
@Controller
@RequestMapping("/")
public class IndexControler {
    
    @GetMapping
    public ModelAndView abrir() {
        return new ModelAndView("index");
    }
}
