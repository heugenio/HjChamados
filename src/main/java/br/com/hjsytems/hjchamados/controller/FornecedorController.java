package br.com.hjsytems.hjchamados.controller;

import br.com.hjsystems.hjchamados.util.PathPadrao;
import br.com.hjsytems.hjchamados.entity.Fornecedor;
import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import br.com.hjsytems.hjchamados.repository.FornecedorRepository;
import br.com.hjsytems.hjchamados.repository.TiposOcorrenciaRepository;
import java.util.ArrayList;
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
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired private FornecedorRepository iFornecedorRepository;
    @Autowired private TiposOcorrenciaRepository iTiposOcorrenciaRepository;
    
    @GetMapping
    public ModelAndView abrir() {
        return new ModelAndView("fornecedor/manutencao");
    }
    
    @GetMapping(PathPadrao.NOVO)
    public ModelAndView novo() {
        return new ModelAndView("fornecedor/form_fornecedor")
                  .addObject("listTiposOcorrencias", iTiposOcorrenciaRepository.findAll());
    }
    
    @PostMapping(PathPadrao.SALVAR+"/{tipos}")
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Fornecedor fornecedor,@PathVariable TiposOcorrencia[] tipos, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        List<TiposOcorrencia> listaTipos = new ArrayList<>();
        for(TiposOcorrencia tipo : tipos){
            listaTipos.add(tipo);
        }
        fornecedor.setListTiposOcorrencias(listaTipos);
        iFornecedorRepository.save(fornecedor);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping(value = {PathPadrao.LISTAR, "/lista/{nome}"})
    public ModelAndView listaUsuario(@PathVariable Optional<String> nome) {
        String nomeForn = "";
        if(nome.isPresent()) {nomeForn = nome.get();}
        return new ModelAndView("fornecedor/lista_fornecedores").addObject("listaFornecedor", iFornecedorRepository.findByNomeContaining(nomeForn));
    }
    
    @GetMapping(PathPadrao.ALTERAR+"{id}")
    public ModelAndView alterar(@PathVariable int id) {
        return new ModelAndView("fornecedor/form_fornecedor")
                .addObject("fornecedor", iFornecedorRepository.getOne(id))
                .addObject("listTiposOcorrencias",iTiposOcorrenciaRepository.findAll());
    }
    
    @GetMapping("/buscarFornecedorporId/{id}")
    public ResponseEntity<Fornecedor> buscarFornPorId(@PathVariable int id) {
        return new ResponseEntity<>(iFornecedorRepository.findById(id).get(),HttpStatus.OK);
    }
    
    @GetMapping("/popularSelectUsuForn")
    public ResponseEntity popularSelectUsuarioFornecedor() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}
