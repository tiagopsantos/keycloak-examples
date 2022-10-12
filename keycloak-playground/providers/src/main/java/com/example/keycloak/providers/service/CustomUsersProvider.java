package com.example.keycloak.providers.service;

import com.example.keycloak.providers.rest.model.PutRequiredActionsRequest;
import com.example.keycloak.providers.rest.model.PutRequiredActionsRequest.Mode;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.models.cache.UserCache;

@RequiredArgsConstructor
@Data
@JBossLog
public class CustomUsersProvider {

  private final KeycloakSession session;


  public void updateRequiredActions(PutRequiredActionsRequest request) {
    var realm = session.realms().getRealm(request.getRealm());
    var pageSize = request.getPageSize();
    if (pageSize == null) {
      session.users().getUsersStream(realm, false)
          .forEach(user -> updateUserRequiredActions(user, request));
    } else {
      var userCount = session.users().getUsersCount(realm, false);
      for (var from = 0; from < userCount; from += pageSize) {
        session.users().getUsersStream(realm, from, pageSize, false)
            .forEach(user -> updateUserRequiredActions(user, request));
      }
    }
  }

  public void updateUserRequiredActions(UserModel user, PutRequiredActionsRequest request) {
    if (request.getMode() == Mode.ADD) {
      request.getActions().forEach(user::addRequiredAction);
    } else if (request.getMode() == Mode.REMOVE) {
      request.getActions().forEach(user::removeRequiredAction);
    } else if (request.getMode() == Mode.REPLACE) {
      var currentActions = user.getRequiredActionsStream().collect(Collectors.toList());
      currentActions.forEach(user::removeRequiredAction);
      request.getActions().forEach(user::addRequiredAction);
    }
    if (request.isCacheEvict()) {
      evictUserFromCacheIfPossible(user);
    }
  }

  private Optional<UserCache> userCache() {
    return Optional.of(session.users())
        .filter(UserCache.class::isInstance)
        .map(UserCache.class::cast);
  }

  private void evictUserFromCacheIfPossible(UserModel user) {
    userCache().ifPresent(cache -> cache.evict(session.getContext().getRealm(), user));
  }

}
