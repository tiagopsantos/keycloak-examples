//package com.example.keycloak.api.client.config;
//
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.assertj.core.util.Lists;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.keycloak.admin.client.resource.RealmResource;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.idm.RealmRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest(classes = {KeycloakConfig.class})
//@RunWith(SpringRunner.class)
//@EnableConfigurationProperties
//@Slf4j
//public class IdentityTest {
//
//  @Autowired
//  private KeycloakApiConfig properties;
//  @Autowired
//  private RealmResource realmResource;
//
//  @Test
//  public void test() {
//    log.warn("--- start ---");
//    log.info("{}", properties);
//    log.info("{}", realmResource);
////    realmResource.users().list()
//    UsersResource usersResource = realmResource.users();
//
//
//    RealmRepresentation realmRepresentation = realmResource.toRepresentation();
//    log.info("realmRepresentation: {}", ToStringBuilder.reflectionToString(realmRepresentation));
//
////    String username = "10038666";
//    String username = "0047290";
//    List<UserRepresentation> users = usersResource.search(username);
//    UserRepresentation user = users.stream()
//        .filter(u -> u.getUsername().equals(username))
//        .findFirst()
//        .orElseThrow(NoSuchElementException::new);
//    log.info("user: {}", ToStringBuilder.reflectionToString(user));
//
//    Map<String, Object> bruteForceUserStatus = realmResource.attackDetection()
//        .bruteForceUserStatus(user.getId());
//    log.info("bruteForceUserStatus: {}", bruteForceUserStatus);
//
//    UserResource userResource = usersResource.get(user.getId());
//    UserRepresentation changes = new UserRepresentation();
//    changes.setEmail("test@bb.com");
//    changes.setEmailVerified(true);
//    changes.setRequiredActions(Lists.emptyList());
//    userResource.update(changes);
//
//    log.warn("--- done ---");
//  }
//
//}