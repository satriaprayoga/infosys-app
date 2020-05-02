package com.infosys.webclient.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.infosys.webclient.config.AppProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenProvider {

	@Autowired
	private AppProperties appProperties;

	public String generateToken(Authentication authentication) {
		UserPrincipal principal=(UserPrincipal)authentication.getPrincipal();
		Date now=new Date();
		Date expiryDate=new Date(now.getTime()+appProperties.getAuth().getTokenExpirationMsec());
		
		return Jwts.builder().setSubject(principal.getId())
							 .setIssuedAt(now)
							 .setExpiration(expiryDate)
							 .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
							 .compact();
	}
	
	public String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Boolean isTokenAuthenticated(String token) {
		String id=getUserIdFromToken(token);
		return id!=null && !isTokenExpired(token);
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
	
	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims=Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
		return claimResolver.apply(claims);
	}
}
