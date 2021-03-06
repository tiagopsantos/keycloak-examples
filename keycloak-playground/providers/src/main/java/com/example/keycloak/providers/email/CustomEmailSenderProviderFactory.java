package com.example.keycloak.providers.email;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config.Scope;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.email.EmailSenderProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@Slf4j
public class CustomEmailSenderProviderFactory implements EmailSenderProviderFactory {

  @Override
  public EmailSenderProvider create(KeycloakSession session) {
    return new CustomEmailSenderProvider(session);
  }

  @Override
  public void init(Scope scope) {
  }

  @Override
  public void postInit(KeycloakSessionFactory sessionFactory) {
  }

  @Override
  public void close() {
  }

  @Override
  public String getId() {
    return "telefonica";
  }
}
