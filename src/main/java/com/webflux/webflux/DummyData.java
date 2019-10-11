package com.webflux.webflux;

import com.webflux.webflux.document.PlayList;
import com.webflux.webflux.repository.PlayListRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

import java.util.UUID;

// @Component
public class DummyData implements CommandLineRunner {

  @Autowired
  private PlayListRespository playListRespository;

  DummyData(PlayListRespository playListRespository) {
    this.playListRespository = playListRespository;
  }

  @Override
  public void run(String... args) throws Exception {

    playListRespository.deleteAll()
        .thenMany(Flux
            .just("Api Rest Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8", "Github",
                "Spring Security", "Web Service RestFull", "Bean no Spring Framework")
            .map(name -> new PlayList(UUID.randomUUID().toString(), name)).flatMap(playListRespository::save))
        .subscribe(System.out::println);

  }

}