package ordemServico.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${jwt.expiration}")
	private Long expitarion;
	@Value("${jwt.secret}")
	private String secret;
	
	public String gerarToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expitarion))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

}
