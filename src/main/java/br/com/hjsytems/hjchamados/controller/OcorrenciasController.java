package br.com.hjsytems.hjchamados.controller;

import br.com.hjsystems.hjchamados.util.EnviaEmail;
import br.com.hjsystems.hjchamados.util.PathPadrao;
import br.com.hjsytems.hjchamados.entity.Ocorrencias;
import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.FornecedorRepository;
import br.com.hjsytems.hjchamados.repository.OcorrenciasRepository;
import br.com.hjsytems.hjchamados.repository.TiposOcorrenciaRepository;
import br.com.hjsytems.hjchamados.repository.UnidadesEmpresariaisRepository;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Autowired
    private OcorrenciasRepository iOcorrenciasRepository;
    @Autowired
    private UnidadesEmpresariaisRepository iUnidadesEmpresariaisRepository;
    @Autowired
    private UsuarioRepository iUsuarioRepository;
    @Autowired
    private TiposOcorrenciaRepository iTiposOcorrenciaRepository;
    @Autowired
    private FornecedorRepository iFornecedorRepository;

    private final String STATUS[] = {"Aberto", "Fechado"};

    @GetMapping
    public ModelAndView preparaEstadoInicial() {
        //EnviaEmail.sendEmail("brunohallef@gmail.com", "Hallef", "Olá e-mail de teste!!! ", "Olá e-mail de teste!");
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
                .addObject("listUnidadeEmpresariais", iUnidadesEmpresariaisRepository.findAll());
    }

    @PostMapping(PathPadrao.SALVAR)
    public ResponseEntity<String> salvar(@Valid @ModelAttribute Ocorrencias oEOcorrencias, BindingResult bindingResult) {
        Usuarios oEUsuarios = new Usuarios();
        oEUsuarios.setId(63);
        oEUsuarios.setNome("Heugenio");
        oEUsuarios.setLogin("123");
        oEUsuarios.setEmail("heugenio@hjsystems.com.br");
        oEUsuarios.setSenha("123");
        oEUsuarios.setUnidadeEmpresarial(oEOcorrencias.getUnidadeEmpresarial());
        oEOcorrencias.setStatus(STATUS[0]);
        oEOcorrencias.setUsuario(oEUsuarios);
        oEOcorrencias.setDataAbertura(removeTime(new Date()));
        oEOcorrencias.setDataFechamento(removeTime(new Date()));

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        iOcorrenciasRepository.save(oEOcorrencias);

        List emais = new LinkedList();
        //emais.add(oEUsuarios.getEmail());
        //emais.add(oEOcorrencias.getFornecedor().getEmail());
        //emais.add(oEOcorrencias.getFornecedor().getEmailAux());
        emais.add("brunohallef@gmail.com");

        Object dados[] = {
            oEUsuarios.getNome(),
            oEOcorrencias.getStatus(),
            oEOcorrencias.getUnidadeEmpresarial().getNome(),
            oEOcorrencias.getUnidadeEmpresarial().getResponsavel(),
            new SimpleDateFormat("dd/MM/yyyy").format(oEOcorrencias.getDataAbertura()),
            oEOcorrencias.getFornecedor().getNome(),
            oEOcorrencias.getDescricao()
        };

        EnviaEmail.sendEmailHtml(emais, oEOcorrencias.getFornecedor().getNome(), "Chamado De Ocorrência!", corpoEmailHtml(dados));

        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping("/updateStatusOcorrencia/{idOcorrencia}/{status}")
    public ResponseEntity updateStatus(@PathVariable int idOcorrencia, String status) {
        Ocorrencias oEOcorrencias = new Ocorrencias();
        iTiposOcorrenciaRepository.getOne(idOcorrencia);
        oEOcorrencias.setStatus(status);
        //iTiposOcorrenciaRepository.save(oEOcorrencias);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/changeSelects/{id}")
    public ResponseEntity<List<TiposOcorrencia>> changeSelects(@PathVariable int id) {
        return new ResponseEntity<>(iFornecedorRepository.findById(id).get().getListTiposOcorrencias(), HttpStatus.OK);
    }

    //Listar por nome fornecedor
    @GetMapping(value = {PathPadrao.LISTAR, "/lista/"})
    public ModelAndView listaOcorrenciasPorFornecedor(String fornecedor, Integer unidades, String status) {
        if (unidades != null && unidades > 0) {
            return new ModelAndView("ocorrencias/lista_ocorrencias")
                    .addObject("listaOcorrencias", iOcorrenciasRepository.findByForStatusUnidade(fornecedor, status, iUnidadesEmpresariaisRepository.getOne(unidades)));
        }
        return new ModelAndView("ocorrencias/lista_ocorrencias").addObject("listaOcorrencias", iOcorrenciasRepository.findByFornecedorStatus(fornecedor, status));
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id) {

        Ocorrencias oEOcorrencias = iOcorrenciasRepository.findById(id).get();

        return new ModelAndView("ocorrencias/form_ocorrencias")
                .addObject("ocorrencia", oEOcorrencias)
                .addObject("listUnidadeEmpresariais", iUnidadesEmpresariaisRepository.findAll())
                .addObject("listFornecedores", iFornecedorRepository.findAll())
                .addObject("listTiposOcorrencias", iFornecedorRepository.findById(oEOcorrencias.getFornecedor().getId()).get().getListTiposOcorrencias());
    }

    private String corpoEmailHtml(Object... dados) {//String usuarioQueAbriu,String status, String unidadeEmpresarial, String responsavelPelaUnidade, String dataAbertura
        String spanStatus;
        if(dados[1].equals(STATUS[0])) {
            spanStatus = "<span style=\"color:green\">"+dados[1]+"</span>";
        } else {
            spanStatus = "<span style=\"color:red\">"+dados[1]+"</span>";
        }
        String html = "<!DOCTYPE html>  <html lang=\"pt-BR\"> "
                + "<head>  "
                + "<meta charset=\"UTF-8\">  "
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">  "
                + "<meta http-equiv='X-UA-Compatible' content='ie=edge'> "
                + "<link href=\"https://fonts.googleapis.com/css?family=Arial:500\" rel=\"stylesheet\"> "
                + "<title>HJSystems</title>  "
                + "<style>\n"
                + "    #divDescricao{\n"
                + "border-radius: 5px;\n"
                + "}\n"
                + "#header {\n"
                + "z-index: 1;\n"
                + "position: absolute;\n"
                + "width: 97.5%;\n"
                + "margin-top: -20px;\n"
                + "height: 60px;\n"
                + "background-color: #00CED1;\n"
                + "margin-bottom: 10px;\n"
                + "}\n"
                + "\n"
                + ".left {\n"
                + "position: relative;\n"
                + "float: left;\n"
                + "margin-top: 50px;\n"
                + "width: 10%;\n"
                + "height: 400px;\n"
                + "background-color: #FF4500;\n"
                + "margin-bottom: 10px;\n"
                + "}\n"
                + "\n"
                + ".right {\n"
                + "position: relative;\n"
                + "float: right;\n"
                + "margin-top: 50px;\n"
                + "width: 88%;\n"
                + "height: 400px;\n"
                + "background-color: #54FF9f;\n"
                + "margin-bottom: 10px;\n"
                + "font-family: Arial Black;\n"
                + "}\n"
                + "\n"
                + "#footer {\n"
                + "position: relative;\n"
                + "height: 50px;\n"
                + "background-color: #00CED1;\n"
                + "clear: both;\n"
                + "font-family: Verdana, sans-serif;\n"
                + "font-size: 14px;\n"
                + "text-align: center;\n"
                + "color: #ffffff;\n"
                + "}\n"
                + ".right p{\n"
                + "float: right;\n"
                + "}\n"
                + "</style>"
                + "</head> "
                + "<body style=\"height: 100%; min-height: 100%;\">\n"
                + "    <div  style=\"margin: 0 auto; width: 600px; text-align: center;\">\n"
                + "        <label style=\"font: 12px sans-serif; color: #ccc\">Este é um e-mail automático, não é necessário respondê-lo.</label>\n"
                + "    </div>\n"
                + "    <div  style=\"margin: 0 auto; width: 600px; border: 2px solid #CCC;\">\n"
                + "        <table style=\"background-color: #ccc; width: 100%;\">\n"
                + "            <tr>\n"
                + "                <td style=\"float: left;\">\n"
                + "                     <p style=\"font: 12px sans-serif; font-weight: bold;\">Chamado aberto por:" + dados[0] + "</p></td>\n"
                + "                <td style=\"text-align: right;\">\n"
                + "                    <label style=\"font: 18px sans-serif; font-weight: bold;\">Status:"+spanStatus+"</label>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "        <div>\n"
                + "          <p>Unidade empresarial: <span style=\"color: blue\">" + dados[2] + "</span> </p>\n"
                + "          <p>Responsável pela unidade: <span style=\"color: blue\">" + dados[3] + "</span></p>\n"
                + "          <p>Data da abertura: <span style=\"color: blue\">" + dados[4] + "</span></p>\n"
                + "          <p>Fornecedor: <span style=\"color: blue\">" + dados[5] + "</span></p>\n"
                + "        </div>\n"
                + "        <p align=\"center\" ><u><b>Descrição da ocorrência</b></u></p>\n"
                + "        <div style=\"box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22); border: 2px solid  #07752e; padding: 10px; border-radius: 25px;\" id=\"divDescricao\">\n" + dados[6]
                + "       </div>\n"
                + "        <table  style=\"margin-top: 5px; width: 100%; background-color: #dddddd;\" cellpadding=\"0\" cellspace=\"0\">\n"
                + "            <tr>\n"
                + "                <td style=\"padding-top: 5px; margin-right: 10px;\">\n"
                + "                </td>\n"
                + "                <td style=\"padding-top: 5px;\">\n"
                + "                    <span style=\"font:200 9px sans-serif;\">© 2000-2014 HJ - Systems, Ltda. Todos os direitos reservados.</span><br>\n"
                + "                </td>\n"
                + "                <td style=\"text-align: right; padding-top: 5px;\">\n"
                + "                    <span style=\"font:200 11px sans-serif;\">Dúvidas e(ou) Sugestões</span> <br>\n"
                + "                    <span style=\"font:200 11px sans-serif;\">atendimento@hjsystems.com.br</span><br>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "</body>";
        return html;
    }
    
    public static Date removeTime(Date data) {
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
        return converteParaDate(formatarDate.format(data), "yyyy-MM-dd");
    }
    
    public static Date converteParaDate(String Data, String formato) {
        DateFormat formatodata = new SimpleDateFormat(formato);
        try {
            Date dataConvertida = formatodata.parse(Data);
            return dataConvertida;
        } catch (ParseException ex) {
            Logger.getLogger(OcorrenciasController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
