package info.devbug.config

import com.fasterxml.classmate.TypeResolver
import com.google.common.collect.Lists.newArrayList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.context.request.async.DeferredResult
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.AlternateTypeRules.newRule
import springfox.documentation.schema.ModelRef
import springfox.documentation.schema.WildcardType
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.ResponseMessage
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate

/**
 * @author Aliaksei Bahdanau.
 */
@Configuration
@EnableSwagger2
open class SwaggerConfig {

    @Autowired
    private val typeResolver: TypeResolver? = null

    @Bean
    open fun digestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().pathMapping("/")
                .directModelSubstitute(LocalDate::class.java, String::class.java).
                genericModelSubstitutes(ResponseEntity::class.java)
                .alternateTypeRules(newRule(typeResolver?.resolve(DeferredResult::class.java, typeResolver.resolve
                (ResponseEntity::class.java, WildcardType::class.java)),
                        typeResolver?.resolve(WildcardType::class.java))).useDefaultResponseMessages(false)
                .globalResponseMessage(
                        RequestMethod.GET, newArrayList<ResponseMessage>(ResponseMessageBuilder().code(500)
                        .message("500 message").responseModel(ModelRef("Error"))
                        .build())).securitySchemes(newArrayList<ApiKey>(apiKey()))
                .securityContexts(newArrayList<SecurityContext>(securityContext()))
    }

    private fun apiKey(): ApiKey {
        return ApiKey("mykey", "api_key", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*")).build()
    }

    internal fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return newArrayList<SecurityReference>(SecurityReference("mykey", authorizationScopes))
    }

    @Bean
    open internal fun security(): SecurityConfiguration {
        return SecurityConfiguration("test-app-client-id", "test-app-realm", "test-app", "apiKey")
    }

    @Bean
    open internal fun uiConfig(): UiConfiguration {
        return UiConfiguration("validatorUrl")
    }
}