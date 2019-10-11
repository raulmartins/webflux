package com.webflux.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlayListRouter {

  @Bean
  public RouterFunction<ServerResponse> route(PlayListHandler handler) {
    System.out.println("teste");
    return RouterFunctions.route(RequestPredicates.GET("/playlist"), handler::findAll)
        .andRoute(RequestPredicates.GET("/playlist/{id}"), handler::findById)
        .andRoute(RequestPredicates.POST("/playlist"), handler::save)
        .andRoute(RequestPredicates.GET("/playlist2/event"), handler::getPlayListByEvents);
  }

}