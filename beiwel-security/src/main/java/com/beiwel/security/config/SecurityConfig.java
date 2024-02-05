package com.beiwel.security.config;

import com.beiwel.security.FailureHandler;
import com.beiwel.security.LogoutSuccess;
import com.beiwel.security.SuccessHandler;
import com.beiwel.security.service.UserDetailServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.util.CollectionUtils;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:security-application-${spring.profiles.active}.properties")
public class SecurityConfig {

  @Value("${spring.profiles.active}")
  private String activeProfile;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  String issuerUri;

  private static final RSAPublicKey rsaPublicKey;
  private static final RSAPrivateKey rsaPrivateKey;

  static {
    try {
      rsaPublicKey = RsaKeyConverters.x509()
          .convert(new ClassPathResource("certs/public.pem").getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static {
    try {
      rsaPrivateKey = RsaKeyConverters.pkcs8()
          .convert(new ClassPathResource("certs/private.pem").getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Autowired
  private SuccessHandler successHandler;

  @Autowired
  private FailureHandler failureHandler;

  @Autowired
  private LogoutSuccess logoutSuccess;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .antMatchers(
                    "/api-docs/**",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/webjars/springfox-swagger-ui/**",
                    "/api/v1/professionals/**",
                    "/api/v1/session"
                )
                .permitAll()
                .anyRequest().authenticated()
        )
        .exceptionHandling()
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
        .formLogin().successHandler(successHandler).failureHandler(failureHandler).and().logout()
        .logoutSuccessHandler(logoutSuccess).clearAuthentication(true).invalidateHttpSession(false)
        .and().userDetailsService(userDetailService).sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2ResourceServer(
            rs -> rs.jwt(jwt -> jwt.decoder(jwtDecoder())
                .jwtAuthenticationConverter(jwtAuthenticationConverter())));
    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
    OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withIssuer);
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    decoder.setJwtValidator(validator);
    return decoder;
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    JWK key = new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build();
    JWKSet jwkSet = new JWKSet(key);
    return new ImmutableJWKSet<>(jwkSet);
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
    grantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

}
