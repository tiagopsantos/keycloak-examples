package com.example.keycloak.providers.email.smtp;

import org.subethamail.smtp.*;

public class LogMessageHandlerFactory implements MessageHandlerFactory {

  public MessageHandler create(MessageContext ctx) {
    return new LogMessageHandler(ctx);
  }

}