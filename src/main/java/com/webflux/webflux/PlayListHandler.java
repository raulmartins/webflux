package com.webflux.webflux;

import com.webflux.webflux.document.PlayList;
import com.webflux.webflux.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@Component
public class PlayListHandler {

  @Autowired
  private PlayListService service;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok().body(service.findALL(), PlayList.class);
  }

   public Mono<ServerResponse> getPlayListByEvents(ServerRequest request) {
     Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
     Flux<PlayList> events = service.findALL();

     return ServerResponse.ok().body(streamPlayList(events, interval), PlayList.class);
   }

   private Flux<PlayList> streamPlayList(Flux<PlayList> events, Flux<Long> interval) {
     return Flux.zip(events, interval, (key, value) -> key);
   }

  public Mono<ServerResponse> findById(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse.ok().body(service.findById(id), PlayList.class);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    final Mono<PlayList> playList = request.bodyToMono(PlayList.class);
    return ServerResponse.ok().body(Mono.from(playList.flatMap(service::save)), PlayList.class);
  }

}