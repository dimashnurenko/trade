package com.trade.swagger;

import com.google.common.collect.Lists;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

	private final List<Parameter> accessTokenParam = Lists.newArrayList(new ParameterBuilder().name("AccessToken")
	                                                                                          .description("access token")
	                                                                                          .parameterType("header")
	                                                                                          .modelRef(new ModelRef("string"))
	                                                                                          .required(false).build());

	//	@Bean
	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.globalOperationParameters(accessTokenParam)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.trade.web"))
//				.build();
		return null;
	}
}
