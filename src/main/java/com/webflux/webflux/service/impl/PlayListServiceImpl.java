package com.webflux.webflux.service.impl;

import com.webflux.webflux.document.PlayList;
import com.webflux.webflux.repository.PlayListRespository;
import com.webflux.webflux.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayListServiceImpl implements PlayListService {

  @Autowired
  private PlayListRespository playListRespository;

  @Override
  public Flux<PlayList> findALL() {
    return playListRespository.findAll();
  }

  @Override
  public Mono<PlayList> findById(String id) {
    return playListRespository.findById(id);
  }

  @Override
  public Mono<PlayList> save(PlayList playList) {
    return playListRespository.save(playList);
  }

}
