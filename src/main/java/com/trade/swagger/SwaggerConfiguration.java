package com.trade.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("init")
				.globalOperationParameters(tokenParam)
				.select()
				.apis(basePackage("com.trade.web"))
				.paths(regex("/.*"))
				.build();
	}

	private List<Parameter> tokenParam = Lists.newArrayList(
			new ParameterBuilder()
					.name("Authorization")
					.description("authorization token")
					.parameterType("header")
					.modelRef(new ModelRef("string"))
					.required(false).build(),
			new ParameterBuilder()
					.name("UserId")
					.parameterType("header")
					.modelRef(new ModelRef("string"))
					.required(false).build()
	                                                       );
}
