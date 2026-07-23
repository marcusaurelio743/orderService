package ordemServico.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ordemServico.domain.dto.CredenciaisDto;

public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;

	public JwtAutenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			try {
				CredenciaisDto ced = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDto.class);
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(ced.getEmail(), ced.getSenha(), new ArrayList<>());
				
				Authentication authentication = authenticationManager.authenticate(authenticationToken);
				return authentication;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String userName = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.gerarToken(userName);
		
		response.setHeader("acess-control-exponse-headers", "Authorization");
		response.setHeader("Authorization", "Bearer "+token);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		response.setStatus(401);
		response.setContentType("Application/json");
		response.getWriter().append(json());
	}

	
		private CharSequence json() {
			long date = new Date().getTime();
				return "{"
					+ "\"timestamp\": " + date + ", " 
					+ "\"status\": 401, "
					+ "\"error\": \"Não autorizado\", "
					+ "\"message\": \"Email ou senha inválidos\", "
					+ "\"path\": \"/login\"}";
		}
	

}
