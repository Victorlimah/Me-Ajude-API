package br.ufpb.dcx.projeto.dcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.ufpb.dcx.projeto.dcs.filters.TokenFilterJWT;

@SpringBootApplication
public class Application {

	@Bean
	public FilterRegistrationBean<TokenFilterJWT> filterJwt() {
		FilterRegistrationBean<TokenFilterJWT> filterRB = new FilterRegistrationBean<TokenFilterJWT>();
		filterRB.setFilter(new TokenFilterJWT());
		filterRB.addUrlPatterns("/v1/api/users/*", "/v1/api/campaigns/*", "/v1/api/donations/*");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
