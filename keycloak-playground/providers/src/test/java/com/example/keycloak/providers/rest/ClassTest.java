package com.example.keycloak.providers.rest;

import com.example.keycloak.providers.rest.GreetingRealmResource.Provider;
import com.example.keycloak.providers.rest.GreetingRealmResource.ProviderFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ClassTest {

  @Test
  void test() {
    debugClass(GreetingRealmResource.class);
    debugClass(ProviderFactory.class);
    debugClass(Provider.class);
  }

  void debugClass(Class<?> clazz) {
    log.info("------------");
    log.info("Name: {}", clazz.getName());
    log.info("SimpleName: {}", clazz.getSimpleName());
    log.info("CanonicalName: {}", clazz.getCanonicalName());
  }

}
