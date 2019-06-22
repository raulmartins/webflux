package com.webflux.webflux.repository;

import com.webflux.webflux.document.PlayList;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlayListRespository extends ReactiveMongoRepository<PlayList, String> {

}