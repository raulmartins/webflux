package com.webflux.webflux;

import com.webflux.webflux.document.PlayList;
import com.webflux.webflux.service.PlayListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

// @Component
public class PlayListHandler {

  @Autowired
  private PlayListService service;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok().body(service.findALL(), PlayList.class);
  }

  // public Mono<ServerResponse> getPlayListByEvents(ServerRequest request) {
  // // System.out.println("*********************Entrou no event");
  // Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
  // Flux<PlayList> events = service.findALL();
  // Flux.zip(interval, events);
  // return ServerResponse.ok().prod;
  // }

  public Mono<ServerResponse> findById(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse.ok().body(service.findById(id), PlayList.class);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    final Mono<PlayList> playList = request.bodyToMono(PlayList.class);
    return ServerResponse.ok().body(Mono.from(playList.flatMap(service::save)), PlayList.class);
  }

}