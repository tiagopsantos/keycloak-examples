package com.example.keycloak.providers.email;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import org.keycloak.email.*;
/**
 * @author <a href="mailto:tiago.santos@link.pt">Tiago Santos</a>
 * Copia - https://github.com/keycloak/keycloak/blob/main/services/src/main/java/org/keycloak/email/DefaultEmailSenderProvider.java
 */
public class CustomEmailSenderProviderFactory implements EmailSenderProviderFactory  {
 
    @Override
    public CustomEmailSenderProvider create(KeycloakSession session) {
        return new CustomEmailSenderProvider(session);
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "default";
    }
}
