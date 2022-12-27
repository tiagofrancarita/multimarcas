package br.com.franca.lojasmulti;

import br.com.franca.lojasmulti.controller.AcessoController;
import br.com.franca.lojasmulti.model.Acesso;
import br.com.franca.lojasmulti.repository.AcessoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class TesteAcessos {

    private AcessoController acessoController;
    private AcessoRepository acessoRepository;

    @Autowired
    public TesteAcessos(AcessoController acessoController, AcessoRepository acessoRepository) {
        this.acessoController = acessoController;
        this.acessoRepository = acessoRepository;
    }

    @Test
    public void testeCadastroAcesso(){

        /*
            Teste cadastrar um acesso
         */

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN_TEST");
        acesso = acessoController.salvarAcesso(acesso).getBody();
        assertEquals(true, acesso.getId() > 0);

    }
    @Test
    public void testeCarregarAcesso(){

        /*
           Teste carregar acesso
        */

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN_TEST");
        acesso = acessoController.salvarAcesso(acesso).getBody();
        assertEquals(true, acesso.getId() > 0);

        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
        assertEquals(acesso.getId(), acesso2.getId());

    }

    @Test
    public void testeDeletarAcesso(){

        /*
          Teste deletar acesso
        */

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN_TEST");
        acesso = acessoController.salvarAcesso(acesso).getBody();
        assertEquals(true, acesso.getId() > 0);

        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
        assertEquals(acesso.getId(), acesso2.getId());

        acessoRepository.deleteById(acesso2.getId());
        acessoRepository.flush();
        Acesso acessoTesteDeletar = acessoRepository.findById(acesso2.getId()).orElse(null);
    }

    @Test
    public void testeQuery(){

       Acesso acessoTesteQuery = new Acesso();
       acessoTesteQuery.setDescricao("ROLE_ALUNO");
       acessoTesteQuery = acessoController.salvarAcesso(acessoTesteQuery).getBody();
       List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
       assertEquals(1, acessos.size());
       acessoRepository.deleteById(acessoTesteQuery.getId());

    }
}