package com.example.keycloak.providers.email;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.email.EmailException;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;

@Slf4j
@RequiredArgsConstructor
public class DelegatingSmtpServerEventListenerProvider implements EventListenerProvider {

  @Override
  public void onEvent(Event event) {
    log.info("{}.onEvent :: {}", getClass().getSimpleName(), event);
  }

  @Override
  public void onEvent(AdminEvent event, boolean includeRepresentation) {
    log.info("{}.onEvent[admin] :: {}", getClass().getSimpleName(), event);
  }

  @Override
  public void close() {
    log.info("{}.close", getClass().getSimpleName());
  }
}
