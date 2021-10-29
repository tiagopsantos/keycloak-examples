package com.example.keycloak.providers.authentication.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.common.util.ResteasyProvider;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.representations.idm.UserRepresentation;

@RequiredArgsConstructor
public class CreateTestUserByApiAuthenticator implements Authenticator {

  private final String username;
  private final HttpClient httpClient;
  private final ObjectMapper objectMapper = new ObjectMapper();


  @Override
  public void authenticate(AuthenticationFlowContext context) {
    try {
      UserRepresentation user = new UserRepresentation();
      user.setUsername(username);
      user.setEnabled(true);

      HttpPost request = new HttpPost("http://host.docker.internal:8080/v1/users");
      request.setEntity(new StringEntity(objectMapper.writeValueAsString(user)));
      request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
      request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

      HttpResponse response = httpClient.execute(request);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode >= 200 && statusCode < 300) {
        if (context.getUser() == null) {
          UserModel userByUsername = context.getSession().users()
              .getUserByUsername(context.getRealm(), username);
          context.setUser(userByUsername);
        }
        context.success();
      } else {
        context.failure(AuthenticationFlowError.INTERNAL_ERROR);
      }
    } catch (Exception e) {
      context.failure(AuthenticationFlowError.INTERNAL_ERROR);
    }
  }

  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom().build();
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
