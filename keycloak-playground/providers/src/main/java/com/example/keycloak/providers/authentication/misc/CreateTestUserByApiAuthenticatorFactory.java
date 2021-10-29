package com.example.keycloak.providers.authentication.misc;

import static java.util.Optional.ofNullable;

import com.example.keycloak.providers.config.ConfigProviders;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.connections.httpclient.HttpClientFactory;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderFactory;

public class CreateTestUserByApiAuthenticatorFactory implements AuthenticatorFactory {

  private String username;


  @Override
  public String getId() {
    return "create-test-user-by-api";
  }

  @Override
  public void init(Scope config) {
    Scope scopedConfig = ConfigProviders.environment().scope("authentication.createTestUser");
    username = ofNullable(scopedConfig.get("username"))
        .orElse("testuser");
  }

  @Override
  public void postInit(KeycloakSessionFactory factory) {
    // not used
  }

  @Override
  public Authenticator create(KeycloakSession session) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    return new CreateTestUserByApiAuthenticator(username, httpClient);
  }

  @Override
  public void close() {
    // not used
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
    return "Create Test User by Api";
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
