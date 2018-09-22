package com.amazonaws.lambda.api.authorizer;

public class TokenParser {
  String token;

  public String getID() {
    String id = token.split(":")[0];
    return id;
  }

  public String getHash() {
    String hash = token.split(":")[1];
    return hash;
  }

  TokenParser(String token) {
    this.token = token;
  }
}
