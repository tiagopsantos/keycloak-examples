package com.example.keycloak.providers.rest.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PutRequiredActionsRequest {

  private List<String> actions;
  private Mode mode;

  public static enum Mode {
    ADD, REMOVE, REPLACE
  }

}