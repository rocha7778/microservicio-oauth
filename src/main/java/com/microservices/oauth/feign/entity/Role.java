package com.microservices.oauth.feign.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Role {
	private Long id;
	private String name;

}
