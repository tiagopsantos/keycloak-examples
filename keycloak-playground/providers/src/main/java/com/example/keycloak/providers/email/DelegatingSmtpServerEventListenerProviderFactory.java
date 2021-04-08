package com.example.keycloak.providers.email;

import com.example.keycloak.providers.email.smtp.LogMessageHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.subethamail.smtp.server.SMTPServer;

@Slf4j
public class DelegatingSmtpServerEventListenerProviderFactory implements
    EventListenerProviderFactory {

  private SMTPServer smtpServer;

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    log.info("{}.create", getClass().getSimpleName());
    return new DelegatingSmtpServerEventListenerProvider();
  }

  @Override
  public void init(Scope scope) {
    log.info("{}.init :: SMTP Server :: starting", getClass().getSimpleName());
    LogMessageHandlerFactory myFactory = new LogMessageHandlerFactory();
    smtpServer = new SMTPServer(myFactory);
    smtpServer.setPort(25000);
    smtpServer.start();
  }

  @Override
  public void postInit(KeycloakSessionFactory sessionFactory) {
  }

  @Override
  public void close() {
    log.info("{}.close :: SMTP Server :: stopping", getClass().getSimpleName());
    smtpServer.stop();
  }

  @Override
  public String getId() {
    return "delegating-smtp-server";
  }
}
