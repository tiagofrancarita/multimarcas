package br.com.franca.lojasmulti;

import br.com.franca.lojasmulti.controller.AcessoController;
import br.com.franca.lojasmulti.model.Acesso;
import br.com.franca.lojasmulti.repository.AcessoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class TesteAcessos {

    private AcessoController acessoController;
    private AcessoRepository acessoRepository;
    private WebApplicationContext wac;

    @Autowired
    public TesteAcessos(AcessoController acessoController, AcessoRepository acessoRepository, WebApplicationContext wac) {
        this.acessoController = acessoController;
        this.acessoRepository = acessoRepository;
        this.wac = wac;
    }

    @Test
    public void testeRestApiCadastroAcesso() throws JsonProcessingException,Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acessoTesteAPI = new Acesso();
        acessoTesteAPI.setDescricao("ROLE_COMPRADOR");

        ObjectMapper objectMapper = new ObjectMapper();


            ResultActions retornoAPI = mockMvc.
                                        perform(MockMvcRequestBuilders.post("/acessoTesteAPI")
                                                .content(objectMapper.writeValueAsString(acessoTesteAPI))
                                                .accept(MediaType.APPLICATION_JSON)
                                                .contentType(MediaType.APPLICATION_JSON));

    }


    @Test
    public void testeCadastroAcesso(){



        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN_TEST");
        acesso = acessoController.salvarAcesso(acesso).getBody();
        assertEquals(true, acesso.getId() > 0);

    }
    @Test
    public void testeCarregarAcesso(){



        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN_TEST");
        acesso = acessoController.salvarAcesso(acesso).getBody();
        assertEquals(true, acesso.getId() > 0);

        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
        assertEquals(acesso.getId(), acesso2.getId());

    }

    @Test
    public void testeDeletarAcesso(){



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