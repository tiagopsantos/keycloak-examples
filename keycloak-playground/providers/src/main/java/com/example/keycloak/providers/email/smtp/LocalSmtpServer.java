package com.example.keycloak.providers.email.smtp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSessionFactory;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

@Slf4j
@RequiredArgsConstructor
public class LocalSmtpServer {

  public static final String DEFAULT_SMTP_SERVER_CONFIG_SCOPE = "smtp.server";
  private final Scope scopedConfig;
  private final KeycloakSessionFactory sessionFactory;
  private SMTPServer smtpServer;

  public void start() {
    log.info("{}.init :: SMTP Server :: starting", getClass().getSimpleName());
    Integer port = scopedConfig.getInt("port", 25);
    boolean debug = scopedConfig.getBoolean("debug", false);
    log.info("{}.init :: SMTP Server :: using port={} | debug={}",
        getClass().getSimpleName(), port, debug);

    MessageHandlerFactory handlerFactory = new LogMessageHandlerFactory();
//    MessageHandlerFactory handlerFactory = debug
//        ? new LogMessageHandlerFactory()
//        : new LogMessageHandlerFactory();
    smtpServer = new SMTPServer(handlerFactory);
    smtpServer.setPort(port);
    smtpServer.start();
  }

  public void stop() {
    log.info("{}.close :: SMTP Server :: stopping", getClass().getSimpleName());
    smtpServer.stop();
  }

}
