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
					.antMatchers(HttpMethod.GET, "/api/restaurant/**").permitAll() // pour voir les restaurants sans être authentifié
					.antMatchers(HttpMethod.GET, "/api/user/{login}").permitAll() // pour vérifier que le login est dispo
					.antMatchers(HttpMethod.GET, "/api/client/local").hasAnyRole("CLIENT")// pour qu'un client voit ses infos
					.antMatchers(HttpMethod.GET, "/api/cuisinier/local").hasAnyRole("CUISINIER")// pour qu'un cuisinier voit ses infos
					.antMatchers(HttpMethod.GET, "/api/gerant/local").hasAnyRole("GERANT")// pour qu'un gerant voit ses infos
					.antMatchers(HttpMethod.GET, "/api/commande/local").hasAnyRole("CLIENT")
					.antMatchers(HttpMethod.GET, "/api/commande/local").hasAnyRole("CLIENT")
					// .antMatchers(HttpMethod.GET, "/api/plat/allPlat").permitAll() Pour charger les plats des restaurants
					// PUT
					.antMatchers(HttpMethod.PUT, "/api/client/local").hasAnyRole("CLIENT") // pour qu'un client se update
					.antMatchers(HttpMethod.PUT, "/api/cuisinier/local").hasAnyRole("CUISINIER") // pour qu'un client se update
					.antMatchers(HttpMethod.PUT, "/api/gerant/local").hasAnyRole("GERANT") // pour qu'un client se update
					// DELETE
					.antMatchers(HttpMethod.DELETE, "/api/client/local").hasAnyRole("CLIENT")
					.antMatchers(HttpMethod.DELETE, "/api/cuisinier/local").hasAnyRole("CUISINIER")
					.antMatchers(HttpMethod.DELETE, "/api/gerant/local").hasAnyRole("GERANT")
					// ALL OTHER
					// .antMatchers("api/commande/**").hasAnyRole("ADMIN")
					// .antMatchers("api/client/**").hasAnyRole("ADMIN")
					// .antMatchers("api/gerant/**").hasAnyRole("ADMIN")
					// .antMatchers("api/cuisinier/**").hasAnyRole("ADMIN")
					// FIN
					.antMatchers("/api/auth").authenticated()
					.antMatchers("/api/**").hasAnyRole("ADMIN")
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
