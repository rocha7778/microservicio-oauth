package com.microservices.oauth.feign.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.oauth.feign.entity.Usuario;



@FeignClient(value = "servicio-usuario" , url="http://usuario:8082")
public interface UsuarioFeignClient {
	
	@GetMapping("/users/internal/name/{name}")
	public ResponseEntity<Usuario> getByUserName(@PathVariable String name) ;

}
