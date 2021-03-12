package com.example.keycloak.api.client.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakApiConfig.class)
@RequiredArgsConstructor
//@ConditionalOnProperty("keycloak.api")
public class KeycloakConfig {

  private final KeycloakApiConfig properties;


  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(properties.getUrl())
        .realm(properties.getRealm())
        .grantType(properties.getGrantType())
        .clientId(properties.getClientId())
        .clientSecret(properties.getClientSecret())
        .build();
  }

  @Bean
  public RealmResource realmResource(Keycloak keycloak) {
    return keycloak.realm(properties.getRealm());
  }

}