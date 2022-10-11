package com.example.keycloak.providers.config;

import com.google.common.base.CaseFormat;
import java.util.function.Function;
import java.util.stream.Stream;
import org.keycloak.Config.Scope;
import org.keycloak.Config.SystemPropertiesScope;

public class EnvironmentScope extends SystemPropertiesScope implements Scope {

  private final String prefix;
  private final Function<String, String> envVarFormatConverter = CaseFormat.LOWER_CAMEL
      .converterTo(CaseFormat.UPPER_UNDERSCORE)
      .andThen(v -> v.replace('.', '_'));

  public EnvironmentScope(String prefix) {
    super(prefix);
    this.prefix = prefix;
  }

  @Override
  public String get(String key) {
    return get(key, null);
  }

  @Override
  public String get(String key, String defaultValue) {
    String fullKey = prefix + key;
    String envFormatFullKey = envVarFormatConverter.apply(fullKey);
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
