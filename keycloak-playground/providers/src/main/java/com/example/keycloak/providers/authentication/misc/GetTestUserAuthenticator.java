package com.example.keycloak.providers.authentication.misc;

import lombok.RequiredArgsConstructor;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

@RequiredArgsConstructor
public class GetTestUserAuthenticator implements Authenticator {

  private final String username;


  @Override
  public void authenticate(AuthenticationFlowContext context) {
    UserModel userModel = context.getSession().users().getUserByUsername(
        context.getRealm(), username);
    if (context.getUser() == null) {
      context.setUser(userModel);
    }
    context.success();
  }

  @Override
  public void action(AuthenticationFlowContext authenticationFlowContext) {
    // not used
  }

  @Override
  public boolean requiresUser() {
    return false;
  }

  @Override
  public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel,
      UserModel userModel) {
    return false;
  }

  @Override
  public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel,
      UserModel userModel) {
    // not used
  }

  @Override
  public void close() {
    // not used
  }
}
