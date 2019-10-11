package com.webflux.webflux.controller;

import com.webflux.webflux.document.PlayList;
import com.webflux.webflux.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

//@RestController
public class PlayListController {

  @Autowired
  private PlayListService playListService;

  @GetMapping(value = "/playlist")
  public Flux<PlayList> getAllPlayList() {

    return playListService.findALL();
  }

  @GetMapping(value = "playlist/{id}")
  public Mono<PlayList> getPlayListById(@PathVariable String id) {
    System.out.println(playListService.findById(id).toString());
    return playListService.findById(id);
  }

  @PostMapping(value = "/playlist")
  public Mono<PlayList> savePlayList(@RequestBody PlayList playList) {
    return playListService.save(playList);
  }

  @GetMapping(value = "/playlist/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Tuple2<Long, PlayList>> getAllPlayListByEvent() {
    Flux<Long> interval = Flux.interval(Duration.ofSeconds(4));
    Flux<PlayList> events = playListService.findALL();
    return Flux.zip(interval, events);
  }

}