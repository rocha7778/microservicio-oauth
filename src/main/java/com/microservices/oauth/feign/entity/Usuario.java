package com.microservices.oauth.feign.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	private String name;
	private String apellido;
	private String email;
	private List<Role> roles;

}
