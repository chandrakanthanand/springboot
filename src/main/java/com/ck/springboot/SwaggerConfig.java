package com.ck.springboot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final Contact DEFAULT_CONTACT = new Contact("Chandrakanth A", "www.ck.com", "ck@gmail.com");
	  public static final ApiInfo DEFAULT = new ApiInfo("Topics API documentation", "Topics Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_CONSUMERS_PRODUCERS =  new HashSet<String>(Arrays.asList("application/json","application/xml"));
   
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT)
				.produces(DEFAULT_CONSUMERS_PRODUCERS).consumes(DEFAULT_CONSUMERS_PRODUCERS);
	}
}
