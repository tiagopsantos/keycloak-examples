package com.example.keycloak.providers.email;

import static java.util.Optional.ofNullable;

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

  private LocalSmtpServer localSmtpServer;

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    log.info("{}.create", getClass().getSimpleName());
    return new LocalSmtpServerEventListenerProvider();
  }

  @Override
  public void init(Scope scope) {
  }

  @Override
  public void postInit(KeycloakSessionFactory sessionFactory) {
    localSmtpServer = new LocalSmtpServer(
        Config.scope(LocalSmtpServer.DEFAULT_SMTP_SERVER_CONFIG_SCOPE),
        sessionFactory);
    localSmtpServer.start();
  }

  @Override
  public void close() {
    ofNullable(localSmtpServer)
        .ifPresent(LocalSmtpServer::stop);
  }

  @Override
  public String getId() {
    return "local-smtp-server";
  }
}
