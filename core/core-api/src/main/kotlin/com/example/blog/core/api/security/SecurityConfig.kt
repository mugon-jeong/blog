package com.example.blog.core.api.security

import com.example.blog.core.domain.license.LicenseFinder
import com.example.blog.core.enums.Permission
import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity.RequestMatcherConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.crypto.spec.SecretKeySpec

@Configuration
class SecurityConfig(
    @Value("\${jwt.secret}")
    var jwtSecretKey: String,
    private val licenseFinder: LicenseFinder,
) {
    @Bean
    fun siteSecurity(http: HttpSecurity): SecurityFilterChain {
        default(http)
            .securityMatchers { matcher: RequestMatcherConfigurer -> matcher.requestMatchers("/api/*/site/*/wts") }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/v1/site/**").hasAuthority(Permission.SITE_ACCESS.roleName)
                    .anyRequest().authenticated()
            }
            .addFilterAfter(SiteAccessFilter(licenseFinder), BearerTokenAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun moduleSecurity(http: HttpSecurity): SecurityFilterChain {
        default(http)
            .securityMatchers { matcher: RequestMatcherConfigurer -> matcher.requestMatchers("/api/*/site/**") }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/v1/site/**").hasAuthority(Permission.SITE_ACCESS.roleName)
                    .anyRequest().authenticated()
            }
            .addFilterAfter(SiteAccessFilter(licenseFinder), BearerTokenAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun web(http: HttpSecurity): SecurityFilterChain {
        default(http)
            .securityMatchers { matcher: RequestMatcherConfigurer -> matcher.requestMatchers("/api/**") }
            .authorizeHttpRequests {
                it.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/v1/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/member").permitAll()
                    .anyRequest().authenticated()
            }
        return http.build()
    }

    private fun default(http: HttpSecurity): HttpSecurity {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .oauth2ResourceServer { oauth ->
                oauth.jwt {
                    it.decoder(jwtDecoder()).jwtAuthenticationConverter(customJwtAuthenticationConverter())
                }.authenticationEntryPoint(CustomBearerTokenAuthenticationEntryPoint())
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.authenticationEntryPoint(CustomAuthenticationEntryPoint()) // 권한 에러 oauth2ResourceServer의 accessDeniedHandler 보다 먼저 작동
                it.accessDeniedHandler(CustomAccessDeniedHandler())
            }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        configuration.allowedHeaders = listOf("*")
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val algorithm = MacAlgorithm.HS256
        return NimbusJwtDecoder.withSecretKey(SecretKeySpec(jwtSecretKey.toByteArray(), algorithm.getName()))
            .macAlgorithm(algorithm)
            .build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwks: JWKSource<SecurityContext> = ImmutableSecret(jwtSecretKey.toByteArray())
        return NimbusJwtEncoder(jwks)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val grantedAuthoritiesConverter =
            JwtGrantedAuthoritiesConverter().apply {
                setAuthoritiesClaimName("permission")
                setAuthorityPrefix("")
            }
        val jwtAuthenticationConverter =
            JwtAuthenticationConverter().apply {
                setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter)
            }
        return jwtAuthenticationConverter
    }

    @Bean
    fun customJwtAuthenticationConverter(): CustomJwtAuthenticationConverter =
        CustomJwtAuthenticationConverter(
            jwtAuthenticationConverter(),
        )
}
