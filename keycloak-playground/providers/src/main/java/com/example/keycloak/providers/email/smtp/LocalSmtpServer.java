package com.example.keycloak.providers.email.smtp;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config.Scope;
import org.subethamail.smtp.server.SMTPServer;

@Slf4j
public class LocalSmtpServer {

  public static final String DEFAULT_SMTP_SERVER_CONFIG_SCOPE = "smtp.server";
  private SMTPServer smtpServer;

  public void init(Scope scopedConfig) {
    log.info("{}.init :: SMTP Server :: starting", getClass().getSimpleName());
    Integer port = scopedConfig.getInt("port", 25);
    log.info("{}.init :: SMTP Server :: using port {}", getClass().getSimpleName(), port);

    LogMessageHandlerFactory handlerFactory = new LogMessageHandlerFactory();
    smtpServer = new SMTPServer(handlerFactory);
    smtpServer.setPort(port);
    smtpServer.start();
  }

  public void stop() {
    log.info("{}.close :: SMTP Server :: stopping", getClass().getSimpleName());
    smtpServer.stop();
  }

}
