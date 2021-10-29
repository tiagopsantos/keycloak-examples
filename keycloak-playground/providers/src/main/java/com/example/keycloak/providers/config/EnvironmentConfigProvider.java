package com.example.keycloak.providers.config;

import org.keycloak.Config.ConfigProvider;
import org.keycloak.Config.Scope;

public class EnvironmentConfigProvider implements ConfigProvider {

  @Override
  public String getProvider(String spi) {
    return System.getenv("keycloak." + spi + ".provider");
  }

  @Override
  public Scope scope(String... scope) {
    StringBuilder sb = new StringBuilder();
    sb.append("keycloak.");
    for (String s : scope) {
      sb.append(s);
      sb.append(".");
    }
    return new EnvironmentScope(sb.toString());
  }
}
