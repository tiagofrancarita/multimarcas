package br.com.franca.lojasmulti.service;

import br.com.franca.lojasmulti.model.Acesso;
import br.com.franca.lojasmulti.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    private AcessoRepository acessoRepository;

    @Autowired
    public AcessoService(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Acesso salvarAcesso(Acesso acesso) {

        /*Qualquer tipo de validação*/

        return acessoRepository.save(acesso);
    }
}
