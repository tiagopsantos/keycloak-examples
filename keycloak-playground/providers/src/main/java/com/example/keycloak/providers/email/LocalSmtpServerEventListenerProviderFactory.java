package com.example.keycloak.providers.email;

import com.example.keycloak.providers.email.smtp.LocalSmtpServer;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config;
import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@Slf4j
public class LocalSmtpServerEventListenerProviderFactory implements
    EventListenerProviderFactory {

  private LocalSmtpServer localSmtpServer = new LocalSmtpServer();

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    log.info("{}.create", getClass().getSimpleName());
    return new LocalSmtpServerEventListenerProvider();
  }

  @Override
  public void init(Scope scope) {
    localSmtpServer.init(Config.scope(LocalSmtpServer.DEFAULT_SMTP_SERVER_CONFIG_SCOPE));
  }

  @Override
  public void postInit(KeycloakSessionFactory sessionFactory) {
  }

  @Override
  public void close() {
    localSmtpServer.stop();
  }

  @Override
  public String getId() {
    return "local-smtp-server";
  }
}
