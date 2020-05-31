package com.microservices.oauth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.oauth.feign.entity.Usuario;
import com.microservices.oauth.feign.repository.UsuarioFeignClient;

@Service
public class OAuthService implements UserDetailsService {
	
	@Autowired
	private UsuarioFeignClient usuarioFeignRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<Usuario> usuarioResponse = usuarioFeignRepository.getByUserName(username);
		
		if(usuarioResponse.getStatusCode().is4xxClientError()) {
			throw new UsernameNotFoundException("Usuario no existe: " + username);
		}
		
		Usuario usuario = usuarioResponse.getBody();
		
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), 
				true, true, true, authorities);
	}
	
	
	

}
