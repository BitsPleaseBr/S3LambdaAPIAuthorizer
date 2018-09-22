package com.amazonaws.lambda.api.authorizer;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static TokenAuthorizerContext input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();

        AuthPolicy output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        System.out.println(output);
    }
    
    @Test
    public void testTokenParser() {
      String input = "teste@teste:hashstring";
      String output = new TokenParser(input).getEmail();
      assertEquals("teste@teste", output);
      System.out.println(output);
    }
}
