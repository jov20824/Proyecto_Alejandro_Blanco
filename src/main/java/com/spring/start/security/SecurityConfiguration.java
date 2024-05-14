package com.spring.start.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/**
		 * 1. Se desactiva el uso de cookies <br/>
		 * 2. Se activa la configuración CORS con los valores por defecto <br/>
		 * 3. Se desactiva el filtro CSRF <br/>
		 * 4. Se indica que el login y SWAGGER no requiere autenticación<br/>
		 * 5. Se indica que el resto de URLs esten securizadas
		 */
		// @formatter:off
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/asignacionProyecto/**").authenticated()
				.antMatchers(HttpMethod.POST,"/asignacionProyecto").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/asignacionProyecto/**").hasRole("USARIO_BORRADOR")
				.antMatchers(HttpMethod.GET, "/proyecto/**").authenticated()
				.antMatchers(HttpMethod.POST, "/proyecto").hasRole("ROL_ADMINISTRADOR")
				.antMatchers(HttpMethod.PUT, "/proyecto/**/entidad/**").fullyAuthenticated()
				.antMatchers(HttpMethod.DELETE, "/proyecto/**").denyAll()
				.antMatchers(HttpMethod.GET, "/programador/hola").permitAll()
				.antMatchers(HttpMethod.GET, "/programador/**").authenticated()
				.antMatchers(HttpMethod.POST, "/programador").hasRole("ROL_PROGRAMADOR")
				.antMatchers(HttpMethod.PUT, "/programador/**/entidad/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/programador/**").hasRole("ADMINISTRADOR_BORRADOR")
				.anyRequest().authenticated();
		// @formatter:on
	}
    @Bean
    public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("username")
				.password("password")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
   AuthenticationManager authenticationManager(
            HttpSecurity http,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService) throws Exception {
       
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build();
    }
}
