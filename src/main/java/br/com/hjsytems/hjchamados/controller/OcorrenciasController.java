
package br.com.hjsytems.hjchamados.controller;

import br.com.hjsystems.hjchamados.util.PathPadrao;
import br.com.hjsytems.hjchamados.entity.Fornecedor;
import br.com.hjsytems.hjchamados.entity.Ocorrencias;
import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.FornecedorRepository;
import br.com.hjsytems.hjchamados.repository.OcorrenciasRepository;
import br.com.hjsytems.hjchamados.repository.TiposOcorrenciaRepository;
import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
import java.util.Date;
import java.util.List;
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
 * @author Hallef
 */
@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciasController {
    
    @Autowired private OcorrenciasRepository iOcorrenciasRepository;
    @Autowired private UnidadesEmpresariaisRepository iUnidadesEmpresariaisRepository;
    @Autowired private UsuarioRepository iUsuarioRepository;
    @Autowired private TiposOcorrenciaRepository iTiposOcorrenciaRepository;
    @Autowired private FornecedorRepository iFornecedorRepository;
    
    private final String SELECTS[] = {"usuario","unidade","fornecedor","tiposOcorrencia"};
    
    @GetMapping
    public ModelAndView preparaEstadoInicial() {
        return new ModelAndView("ocorrencias/manutencao")
                .addObject("listUnidadeEmpresariais", iUnidadesEmpresariaisRepository.findAll());
                //.addObject("usuariologado","USUÁRIO-LOGADO");
    }
    
    @GetMapping(PathPadrao.NOVO)
    public ModelAndView novo() {
        return new ModelAndView("ocorrencias/form_ocorrencias")
                //.addObject("listTiposOcorrencias", iTiposOcorrenciaRepository.findAll())
                .addObject("listFornecedores", iFornecedorRepository.findAll())
                //.addObject("listUsuarios",iUsuarioRepository.findAll());
                .addObject("listUnidadeEmpresariais",iUnidadesEmpresariaisRepository.findAll());
    }
    
    @PostMapping(PathPadrao.SALVAR)
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Ocorrencias oEOcorrencias, BindingResult bindingResult) {
        Usuarios oEUsuarios = new Usuarios();
        oEUsuarios.setNome("TESTE");
        oEOcorrencias.setUsuario(oEUsuarios);
        oEOcorrencias.setDataAbertura(new Date());
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        iOcorrenciasRepository.save(oEOcorrencias);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping("/changeSelects/{id}")
    public ResponseEntity<List<TiposOcorrencia>> changeSelects(@PathVariable int id) {
        return new ResponseEntity<>(iFornecedorRepository.findById(id).get().getListTiposOcorrencias(),HttpStatus.OK);
    }
}