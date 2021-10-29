package com.example.keycloak.api.client.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRoute {

  public static final String USERS_ROUTE = "/v1/users";

  @Bean
  public RouterFunction<ServerResponse> search(UserHandler handler) {
    return RouterFunctions.route(
        RequestPredicates.GET(USERS_ROUTE)
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        handler::search);
  }

  @Bean
  public RouterFunction<ServerResponse> get(UserHandler handler) {
    return RouterFunctions.route(
        RequestPredicates.GET(USERS_ROUTE + "/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        handler::getUser);
  }

  @Bean
  public RouterFunction<ServerResponse> post(UserHandler handler) {
    return RouterFunctions.route(
        RequestPredicates.POST(USERS_ROUTE)
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        handler::postUser);
  }

}
