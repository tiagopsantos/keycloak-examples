package com.example.keycloak.providers.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.jbosslog.JBossLog;

@Path("/hello")
@JBossLog
public class GreetingResource {

  public GreetingResource() {
    log.infof("Creating %s", getClass().getName());
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from RESTEasy Reactive";
  }

}