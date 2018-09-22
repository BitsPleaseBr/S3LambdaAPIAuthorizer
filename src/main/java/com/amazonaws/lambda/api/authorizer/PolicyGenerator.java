package com.amazonaws.lambda.api.authorizer;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.lambda.api.authorizer.AuthPolicy.HttpMethod;
import com.amazonaws.lambda.api.authorizer.AuthPolicy.PolicyDocument;

public class PolicyGenerator {
  static Map<String, String> resourcesAllowed = new HashMap<>();
  ArnParser arnParser;
  String principalId;

  AuthPolicy buildPolicy(boolean permitir) {
    AuthPolicy auth = new AuthPolicy();
    auth.setPrincipalId(principalId);
    PolicyDocument doc = new PolicyDocument(arnParser.getRegion(), arnParser.getAwsAccountId(),
        arnParser.getRestApiId(), arnParser.getStage());
    if (permitir) {
      doc.allowMethod(HttpMethod.ALL, "/*");
    } else {
      doc.denyMethod(HttpMethod.ALL, "/*");
    }
    auth.setPolicyDocument(doc);
    return auth;
  }

  PolicyGenerator(ArnParser arnParser, String principalId) {
    this.arnParser = arnParser;
    this.principalId = principalId;
  }
}
