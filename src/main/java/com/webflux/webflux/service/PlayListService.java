package com.webflux.webflux.service;

import com.webflux.webflux.document.PlayList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayListService {

  Flux<PlayList> findALL();

  Mono<PlayList> findById(String id);

  Mono<PlayList> save(PlayList playList);

}