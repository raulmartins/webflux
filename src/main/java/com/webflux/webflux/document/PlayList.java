package com.webflux.webflux.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayList {

  @Id
  private String id;
  private String name;

  public PlayList(String id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }
}