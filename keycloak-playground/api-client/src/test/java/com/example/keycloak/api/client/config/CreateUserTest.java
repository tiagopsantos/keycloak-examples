package com.example.keycloak.api.client.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = KeycloakConfig.class)
@Slf4j
class CreateUserTest {

  @Autowired
  Keycloak keycloak;
  @Autowired
  RealmResource realmResource;

  @Test
  void createUser() {
    UserRepresentation user = new UserRepresentation();
    user.setUsername("testuser-dummy");
    user.setEnabled(true);

    realmResource.users().create(user);
  }
}