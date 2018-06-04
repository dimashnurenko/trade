package com.trade.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(tokenParam)
				.select()
				.apis(or(basePackage("com.trade.web"), basePackage("com.trade.auth.web")))
				.paths(PathSelectors.any())
				.build();
	}

	private List<Parameter> tokenParam = newArrayList(new ParameterBuilder().name("Authentication")
	                                                                        .description("authorization token")
	                                                                        .parameterType("header")
	                                                                        .modelRef(new ModelRef("string"))
	                                                                        .required(false)
	                                                                        .build());
}
