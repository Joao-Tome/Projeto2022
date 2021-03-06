package br.edu.toledoprudente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.edu.toledoprudente.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioDAO serviceDao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceDao).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**", "/fonts/**","/img/**").permitAll()
                .antMatchers("/", "/service/getfuncionarios/**", "/**/getimagem/**", "/site/**").permitAll()
                .antMatchers("/home","/produto/**","/email/**","/marca/**", "/noticia/**","/parceiro/**", "/pessoa/**", "/produto/**","/produtostatus/**", "/telefone/**","/tipousuario/**","/usuario/**").hasAuthority("Administrador")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/site", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/site")
                .permitAll();

    }

}
