package com.example.keycloak.providers.config;

import static com.example.keycloak.providers.config.SystemConfigHelper.convertToEnvironmentConfigKey;

import java.util.stream.Stream;
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
    String fullKey = prefix + key;
    String envFormatFullKey = convertToEnvironmentConfigKey(fullKey);
    return Stream.of(envFormatFullKey, fullKey)
        .flatMap(k -> Stream.ofNullable(System.getenv(k)))
        .findFirst()
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
