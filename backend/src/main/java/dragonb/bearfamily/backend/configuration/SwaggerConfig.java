package dragonb.bearfamily.backend.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                // 그룹 이름 설정
                .group("bear_family")
                // Swagger를 적용시킬 주소
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        // Swagger API 명세서 Customizing
        Info info = new Info().title("BearFamily API")
        .description("꼼꼼 가계부 프로젝트 API 명세서입니다.")
        .version("v0.0.1")
        .contact(new Contact().name("DragonB").email("dydqh2648@naver.com"))
        .license(new License().name("BearFamily License v0.0").url("/license"));

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme() 
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT")
        .in(SecurityScheme.In.HEADER)
        .name(HttpHeaders.AUTHORIZATION);

        // Security 요청 설정
        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("JWT");

        return new OpenAPI()
        // Security 인증 컴포넌트 설정
        .components(new Components().addSecuritySchemes("JWT", bearerAuth))
        // API 마다 Security 인증 컴포넌트 설정
        .addSecurityItem(addSecurityItem)
        .info(info);
    }
}
