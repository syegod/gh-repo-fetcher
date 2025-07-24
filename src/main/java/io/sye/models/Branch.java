package io.sye.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

  private String name;

  private String sha;

  public String name() {
    return name;
  }

  public Branch name(String name) {
    this.name = name;
    return this;
  }

  public String sha() {
    return sha;
  }

  @JsonSetter("commit")
  public Branch unpackOwner(Map<String, Object> commit) {
    this.sha = (String) commit.get("sha");
    return this;
  }

  public Branch sha(String sha) {
    this.sha = sha;
    return this;
  }
}
