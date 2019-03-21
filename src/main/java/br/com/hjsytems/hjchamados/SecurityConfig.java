
package br.com.hjsytems.hjchamados;

import br.com.hjsystems.hjchamados.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Hallef
 */
@EnableWebSecurity
@ComponentScan(basePackageClasses = {AppUserDetailsService.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder());
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("dbo");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/usuario").hasRole("CADASTRAR_USUARIO")
            .antMatchers("/tiposocorrencia").hasRole("CADASTRAR_TIPO_OCORRENCIA")
            .antMatchers("/fornecedor").hasRole("CADASTRAR_FORNECEDOR")
            .antMatchers("/ocorrencias").hasRole("CADASTRAR_OCORRENCIA")
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
    
    @Bean
    public static NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
}
