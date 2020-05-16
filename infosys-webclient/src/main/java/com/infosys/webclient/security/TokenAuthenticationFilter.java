package com.infosys.webclient.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	private TokenProvider tokenProvider;
	
	private CustomUserDetailsService customUserDetailsService;
	
	public TokenAuthenticationFilter(TokenProvider tokenProvider,CustomUserDetailsService customUserDetailsService) {
		this.tokenProvider=tokenProvider;
		this.customUserDetailsService=customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token=getJwtFromRequest(request);
			if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				if(tokenProvider.isTokenAuthenticated(token)) {
					String userId=tokenProvider.getUserIdFromToken(token);
					UserDetails userDetails=customUserDetailsService.loadById(userId);
					UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
					filterChain.doFilter(request, response);
				}
			}else {
				 SecurityContextHolder.clearContext();
			      filterChain.doFilter(request, response);
			      return;
			}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}finally {
			SecurityContextHolder.clearContext();
		}
		
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken=request.getHeader("Authorization");
		 if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7, bearerToken.length());
	        }
		 return null;
	}

}
