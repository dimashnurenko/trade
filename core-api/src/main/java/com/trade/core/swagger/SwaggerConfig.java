package com.trade.core.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//	private final List<Parameter> accessTokenParam = Lists.newArrayList(new ParameterBuilder().name("AccessToken")
//	                                                                                          .description("access token")
//	                                                                                          .parameterType("header")
//	                                                                                          .modelRef(new ModelRef("string"))
//	                                                                                          .required(false).build());

	//				.globalOperationParameters(accessTokenParam)

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.trade.web"))
				.build();
	}
}
