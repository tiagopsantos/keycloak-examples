package com.example.keycloak.providers.authentication.misc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.keycloak.Config.Scope;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.services.messages.Messages;

@RequiredArgsConstructor
public class CancelLoginAuthenticator implements Authenticator, AuthenticatorFactory {

  @Override
  public String getId() {
    return "cancel-login";
  }

  @Override
  public void authenticate(AuthenticationFlowContext context) {
    context.forkWithSuccessMessage(new FormMessage(Messages.ACCOUNT_UPDATED));
//    context.cancelLogin();
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

  @Override
  public void init(Scope config) {
    // not used
  }

  @Override
  public void postInit(KeycloakSessionFactory factory) {
    // not used
  }

  @Override
  public Authenticator create(KeycloakSession session) {
    return this;
  }

  @Override
  public String getReferenceCategory() {
    return null;
  }

  @Override
  public boolean isConfigurable() {
    return false;
  }

  @Override
  public Requirement[] getRequirementChoices() {
    return Requirement.values();
  }

  @Override
  public boolean isUserSetupAllowed() {
    return true;
  }

  @Override
  public String getDisplayType() {
    return "Cancel Login";
  }

  @Override
  public String getHelpText() {
    return getDisplayType();
  }

  @Override
  public List<ProviderConfigProperty> getConfigProperties() {
    return List.of();
  }

}
