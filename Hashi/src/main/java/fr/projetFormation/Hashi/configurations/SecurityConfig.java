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
					.antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/client/**").permitAll() 
					.antMatchers(HttpMethod.GET, "/api/client/**").permitAll()
					.antMatchers(HttpMethod.PUT, "/api/client/**").permitAll()
					.antMatchers(HttpMethod.GET, "/api/restaurant/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/restaurant/**").hasAnyRole("GERANT")
					// .antMatchers(HttpMethod.GET, "/api/cuisinier/**").hasAnyRole("GERANT")
					.antMatchers(HttpMethod.GET, "/api/cuisinier/**").permitAll() // ça devrait ê la ligne du dessus normalement (creation restaurant)
					.antMatchers(HttpMethod.POST, "/api/cuisinier/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/gerant/**").permitAll()
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
