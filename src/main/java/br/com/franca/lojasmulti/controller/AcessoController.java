package br.com.franca.lojasmulti.controller;

import br.com.franca.lojasmulti.model.Acesso;
import br.com.franca.lojasmulti.repository.AcessoRepository;
import br.com.franca.lojasmulti.service.AcessoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/acessos")
@Api(value = "entry-point para gerenciar acessos dos usuarios ''PERMISSÕES'' ", produces = MediaType.APPLICATION_JSON_VALUE,
consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"entrypoint-acessos"})
@Controller
@RestController
public class AcessoController {

    private AcessoService acessoService;
    private AcessoRepository acessoRepository;

    @Autowired
    public AcessoController(AcessoService acessoService, AcessoRepository acessoRepository) {
        this.acessoService = acessoService;
        this.acessoRepository = acessoRepository;
    }


    @ApiOperation(value = "Cadastro de um novo acesso")
    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/salvarAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        Acesso acessoSalvo = acessoService.salvarAcesso(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar um acesso por id")
    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/deleteAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity("Acesso Removido",HttpStatus.OK);
    }
}