package fr.projetFormation.Hashi.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/api/**")
				.csrf().ignoringAntMatchers("/api/**")
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS).permitAll()
					// POST
					.antMatchers(HttpMethod.POST, "/api/client/**").permitAll() // inscription
					.antMatchers(HttpMethod.POST, "/api/cuisinier/**").permitAll() // inscription
					.antMatchers(HttpMethod.POST, "/api/gerant/**").permitAll() // inscription
					.antMatchers(HttpMethod.POST, "/api/commande/**").hasAnyRole("CLIENT") // pour qu'un client commande
					// GET
					.antMatchers(HttpMethod.GET, "/api/restaurant/**").permitAll() // pour commander avant d'être authentifié
					.antMatchers(HttpMethod.GET, "/api/user/{login}").permitAll() // pour vérifier que le login est dispo
					.antMatchers(HttpMethod.GET, "/api/client/**").permitAll()// pour qu'un client voit ses infos
					// PUT
					.antMatchers(HttpMethod.PUT, "/api/client/**").hasAnyRole("CLIENT") // pour qu'un client se update
					.antMatchers(HttpMethod.GET, "/api/commande/**").permitAll()
					.antMatchers("/api/auth").authenticated()
					.antMatchers("/api/**").hasAnyRole("ADMIN","CLIENT","CUISINIER","GERANT")
				.and()
				.httpBasic();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
