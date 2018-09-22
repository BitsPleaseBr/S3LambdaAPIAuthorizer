package com.amazonaws.lambda.api.authorizer;

public class ArnParser {
  String arn;
  String region;
  String awsAccountId;
  String restApiId;
  String httpMethod;
  String resource;
  String stage;

  public String getArn() {
    return arn;
  }

  public void setArn(String arn) {
    this.arn = arn;
    parseArnData();
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getAwsAccountId() {
    return awsAccountId;
  }

  public void setAwsAccountId(String awsAccountId) {
    this.awsAccountId = awsAccountId;
  }

  public String getRestApiId() {
    return restApiId;
  }

  public void setRestApiId(String restApiId) {
    this.restApiId = restApiId;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  private void parseArnData() {
    String[] arnPartials = arn.split(":");
    region = arnPartials[3];
    awsAccountId = arnPartials[4];
    String[] apiGatewayArnPartials = arnPartials[5].split("/");
    restApiId = apiGatewayArnPartials[0];
    stage = apiGatewayArnPartials[1];
    httpMethod = apiGatewayArnPartials[2];
    resource = ""; // root resource
    if (apiGatewayArnPartials.length == 4) {
      resource = apiGatewayArnPartials[3];
    }
  }

  ArnParser(String arn) {
    this.arn = arn;
    parseArnData();
  }

}
