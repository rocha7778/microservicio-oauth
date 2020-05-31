package com.microservices.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.
		tokenKeyAccess("permitAll()").
		checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory().
		withClient("app").
		secret(("123456")).
		scopes("write","read").
		authorizedGrantTypes("password","refresh_token").
		accessTokenValiditySeconds(3600).
		refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter());
		
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public  JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("jxve9BzzkIF11tKPwx0bPWJH_VIKJKDWOno-I-br0JhL00wl2NBZwLmcT10fe_MROLTT0WK9JGN1i6X78xJlaRD1dnIM4NJ7gRc4gZ-3pfX4LFGa5R-9DRehqmZ7MDlTXVMusMm-GfKyJQ7Rnz0rOoD2MgqoC_mmyFy0O5zVUZBH5FoPDzSICFGNuOPoRnJ6MVr5hEatqoBlxr0xYn1eRkqoJnjyGPl9v-NqY31l4rIbYQN5vrTKUBVFIguKi54FNXz_e-Q56Wdrq3ver2s0eYxygWjLlkxRv_xcx_gY0QfFftDEG6lT5lb5U1FMedtwe6UWSsoN2aACx0Zwme_4kw");
		return tokenConverter;
	}
	
	

}
