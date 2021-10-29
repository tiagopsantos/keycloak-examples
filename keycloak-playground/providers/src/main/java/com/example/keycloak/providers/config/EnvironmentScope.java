package com.example.keycloak.providers.config;

import static java.util.Optional.ofNullable;

import org.keycloak.Config.Scope;
import org.keycloak.Config.SystemPropertiesScope;

public class EnvironmentScope extends SystemPropertiesScope implements Scope {

  public EnvironmentScope(String prefix) {
    super(prefix);
  }

  @Override
  public String get(String key) {
    return get(key, null);
  }

  @Override
  public String get(String key, String defaultValue) {
    return ofNullable(System.getenv(prefix + key))
        .orElse(defaultValue);
  }

  @Override
  public Scope scope(String... scope) {
    StringBuilder sb = new StringBuilder();
    sb.append(prefix + ".");
    for (String s : scope) {
      sb.append(s);
      sb.append(".");
    }
    return new EnvironmentScope(sb.toString());
  }
}
