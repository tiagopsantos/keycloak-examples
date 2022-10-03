package com.example.keycloak.providers.email;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.email.EmailException;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.models.KeycloakSession;

@JBossLog
@RequiredArgsConstructor
public class CustomEmailSenderProvider implements EmailSenderProvider {

  private final KeycloakSession session;

  @Override
  public void send(Map<String, String> config, String address, String subject, String textBody,
      String htmlBody) throws EmailException {
    try {
      log.infof("%s.send :: %s | %s | %s | %s | %s",
          getClass().getSimpleName(), config, address, subject, textBody, htmlBody);
    } catch (Exception e) {
      throw new EmailException(e);
    }
  }

  @Override
  public void close() {
    // ignored
  }
}
