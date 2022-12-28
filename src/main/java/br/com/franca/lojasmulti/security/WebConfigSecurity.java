package br.com.franca.lojasmulti.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, "/acessos/salvarAcesso", "/acessos/deleteAcesso")
                .antMatchers(HttpMethod.POST, "/acessos/salvarAcesso", "/acessos/deleteAcesso")
                .antMatchers("/manos-multi/swagger-ui/index.html/");
        /*Ingnorando URL no momento para nao autenticar*/
    }

}

