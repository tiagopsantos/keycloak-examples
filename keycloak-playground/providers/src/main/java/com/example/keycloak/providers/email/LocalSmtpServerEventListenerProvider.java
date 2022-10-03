package com.example.keycloak.providers.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

@JBossLog
@RequiredArgsConstructor
public class LocalSmtpServerEventListenerProvider implements EventListenerProvider {

  @Override
  public void onEvent(Event event) {
    // not needed
  }

  @Override
  public void onEvent(AdminEvent event, boolean includeRepresentation) {
    // not needed
  }

  @Override
  public void close() {
    // not needed
  }
}
