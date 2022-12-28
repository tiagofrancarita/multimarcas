package br.com.franca.lojasmulti.controller;

import br.com.franca.lojasmulti.model.Acesso;
import br.com.franca.lojasmulti.repository.AcessoRepository;
import br.com.franca.lojasmulti.service.AcessoService;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping
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

    @ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarAcesso") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/
		
		Acesso acessoSalvo = acessoService.salvarAcesso(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/deleteAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<String> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity<String>("Acesso Removido",HttpStatus.OK);
    }
    
    @ResponseBody /*Poder dar um retorno da API*/
    @DeleteMapping(value = "/deleteAcessoPorId{idAcesso}") /*Mapeando a url para receber JSON*/
    public ResponseEntity<String> deleteAcessoPorId(@PathVariable("idAcesso")Long idAcesso) { /*Recebe o JSON e converte pra Objeto*/

        acessoRepository.deleteById(idAcesso);

        return new ResponseEntity<String>("Acesso Removido",HttpStatus.OK);
    }
    
    @ResponseBody
	@GetMapping(value = "**/obterAcesso/{idAcesso}")
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("idAcesso") Long idAcesso) { 
		
		Acesso acesso = acessoRepository.findById(idAcesso).get();
		
		return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarPorDesc/{desc}")
	public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);
		
		return new ResponseEntity<List<Acesso>>(acesso,HttpStatus.OK);
	}
}