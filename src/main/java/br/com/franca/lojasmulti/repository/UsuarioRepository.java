package br.com.franca.lojasmulti.repository;

import br.com.franca.lojasmulti.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);


}
