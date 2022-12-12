package com.security.jwt;

import com.security.jwt.modele.utilisateur;
import com.security.jwt.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class securiconfig extends WebSecurityConfigurerAdapter {
   @Autowired
    private serviceImpl s;

    @Override
    // c'est ici qu'on doit dire a spring comment il doit chercher les utilisateurs et les roles
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // quand l'utilisteur va s'authentifier utilise moi la methode pour chercher l'utilisateur
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             utilisateur u1=s.loadUserByName(username);
                Collection<GrantedAuthority> a=new ArrayList<>();
                u1.getRoles().forEach(role -> {
                    role.getRolename();
                });
                return new User(u1.getNom(),u1.getMot2passe(),a);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
        //http.authorizeRequests().antMatchers()
       // http.authorizeRequests().antMatchers(HttpMethod.GET,"/read/**").hasAuthority("ADMIN");
       // http.authorizeRequests().antMatchers(HttpMethod.POST,"/read/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated() ;
        http.oauth2Login();
        // demander a spring d'afficher le formulaire d'authentification
        http.addFilter(new jwtAuthentificationFilters(authenticationManagerBean()) );
        http.addFilterBefore(new jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
