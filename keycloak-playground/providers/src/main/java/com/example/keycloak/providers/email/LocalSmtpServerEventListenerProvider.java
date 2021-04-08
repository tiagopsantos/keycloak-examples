package com.example.keycloak.providers.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

@Slf4j
@RequiredArgsConstructor
public class LocalSmtpServerEventListenerProvider implements EventListenerProvider {

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
