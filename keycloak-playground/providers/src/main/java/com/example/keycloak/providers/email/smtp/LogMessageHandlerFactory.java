package com.example.keycloak.providers.email.smtp;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

public class LogMessageHandlerFactory implements MessageHandlerFactory {

  public MessageHandler create(MessageContext ctx) {
    return new LogMessageHandler(ctx);
  }

}