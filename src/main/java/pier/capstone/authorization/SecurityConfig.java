package pier.capstone.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JWTAuthFilter jwtAuthFilter;
	
	@Autowired
	CorsFilter corsFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable());
		
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());

		http.authorizeHttpRequests(auth -> {
	        auth.requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.GET, "/users/me").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN");
	    });
		
		http.authorizeHttpRequests(auth -> {
	        auth.requestMatchers(HttpMethod.GET, "/posts").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.GET, "/posts/**").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.POST, "/posts/**").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers(HttpMethod.PUT, "/posts/**").hasAnyAuthority("USER", "ADMIN");
	        auth.requestMatchers("/posts/**").hasAnyAuthority("USER", "ADMIN");
	    });
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.POST, "/likes/**").hasAnyAuthority("USER", "ADMIN");
			auth.requestMatchers(HttpMethod.DELETE, "/likes/**").hasAnyAuthority("USER", "ADMIN");
		});
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/libraries").hasAnyAuthority("USER", "ADMIN");
			auth.requestMatchers("/libraries/**").hasAnyAuthority("USER", "ADMIN");
		});
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("USER", "ADMIN");
			auth.requestMatchers("/products/**").hasAnyAuthority("USER", "ADMIN");
		});
		
		http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
	
	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
