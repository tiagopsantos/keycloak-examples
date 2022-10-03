package com.example.keycloak.providers.email;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.email.EmailException;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.models.KeycloakSession;

@Slf4j
@RequiredArgsConstructor
public class CustomEmailSenderProvider implements EmailSenderProvider {

  private final KeycloakSession session;

  @Override
  public void send(Map<String, String> config, String address, String subject, String textBody,
      String htmlBody) throws EmailException {
    try {
      log.info("{}.send :: {} | {} | {} | {} | {}",
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
