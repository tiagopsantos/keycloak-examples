package com.example.keycloak.providers.config;

import lombok.experimental.UtilityClass;
import org.keycloak.Config.ConfigProvider;
import org.keycloak.Config.SystemPropertiesConfigProvider;

@UtilityClass
public class ConfigProviders {

  public static ConfigProvider environment() {
    return new EnvironmentConfigProvider();
  }

  public static ConfigProvider systemProperties() {
    return new SystemPropertiesConfigProvider();
  }

}
