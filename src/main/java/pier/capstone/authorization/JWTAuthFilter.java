package pier.capstone.authorization;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UnauthorizedException;
import pier.capstone.services.UserService;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (!request.getMethod().equals("OPTIONS")) {
		//questo metodo verrà lanciato ad ogni request
		//si inizia estraendo il token dall'header della richiesta
		String authHeader = request.getHeader("Authorization");
		
		//controllo validità del token
		if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Ricorda di inserire il token nella richiesta!");
		
		//faccio il substring per saltare la stringa "Bearer " (7 caratteri) e ottengo il token pulito
		String accesToken = authHeader.substring(7);
		
		JWTTools.isTokenValid(accesToken);
		
		//se va tutto bene lavoro sul token
		String email = JWTTools.extractSubject(accesToken);
		
		try {
			User user = userService.findUserByEmail(email);
			
			//una volta trovato l'utente lo aggiungo al SecurityContextHolder
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			
			// accedo al prossimo blocco della filterChain
			filterChain.doFilter(request, response);
			
		} catch (NotFoundException e){
			//se non trova l'utente lancia un not found
			e.printStackTrace(); }
//		} catch (org.springframework.data.crossstore.ChangeSetPersister.NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		} else {
			filterChain.doFilter(request, response);
		}
		
	}
	
	//questo serve ad evitare che il filtro venga eseguito per ogni richiesta
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}
}
