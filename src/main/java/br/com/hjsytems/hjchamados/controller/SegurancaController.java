
package br.com.hjsytems.hjchamados.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hallef
 */
@Controller
public class SegurancaController {
    
    @RequestMapping("/login")
    public String login(@AuthenticationPrincipal User user) {
        if(user!=null) {
            return "redirect:/";
        }
        return "login";
    }
    
    @RequestMapping("/403")//sem permissão
    public ModelAndView pagina403() {
        return new ModelAndView("/pagina403");
    }
    
}
