package com.example.keycloak.providers.authentication.misc;

import static java.util.Optional.ofNullable;

import com.example.keycloak.providers.config.ConfigProviders;
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
import org.keycloak.provider.ProviderConfigProperty;

@RequiredArgsConstructor
public class GetTestUserAuthenticator implements Authenticator, AuthenticatorFactory {

  private String username;

  @Override
  public String getId() {
    return "get-test-user";
  }

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

  @Override
  public void init(Scope config) {
    Scope scopedConfig = ConfigProviders.environment().scope("authentication.getTestUser");
    username = ofNullable(scopedConfig.get("username"))
        .orElse("testuser");
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
    return "Get Test User";
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
