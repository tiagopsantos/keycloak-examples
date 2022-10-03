package com.example.keycloak.providers.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

@Path("/realm/hello")
@JBossLog
public class GreetingRealmResource {

  public GreetingRealmResource() {
    log.infof("Creating %s", getClass().getName());
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from RESTEasy Reactive";
  }

  public static class ProviderFactory implements RealmResourceProviderFactory {

    public ProviderFactory() {
      log.infof("Creating %s", getClass().getName());
    }

    @Override
    public RealmResourceProvider create(KeycloakSession keycloakSession) {
      return new Provider();
    }

    @Override
    public void init(Scope scope) {
      // not needed
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
      // not needed
    }

    @Override
    public void close() {
      // not needed
    }

    @Override
    public String getId() {
      return getClass().getSimpleName();
    }

  }

  public static class Provider implements RealmResourceProvider {

    public Provider() {
      log.infof("Creating %s", getClass().getName());
    }

    @Override
    public Object getResource() {
      return new GreetingResource();
    }

    @Override
    public void close() {
      // not needed
    }
  }

}