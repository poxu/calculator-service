package com.calculator;

import com.calculator.beans.NumberExpression;
import com.calculator.beans.OperationExpression;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class RecursiveCalculatorResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testCalculate() {
        OperationExpression expression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        expression.parts.add(new NumberExpression(1L));
        expression.parts.add(new NumberExpression(2L));

        NumberExpression responseMsg = target.path("recursive")
                .request("application/xml")
                .post(Entity.entity(expression, "application/xml"))
                .readEntity(NumberExpression.class);
        assertEquals(new Long(3L), responseMsg.getValue());
    }

    @Test
    public void testComplexExpression() throws Exception {
        OperationExpression expression;
        expression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        expression.parts.add(new NumberExpression(1L));
        expression.parts.add(new NumberExpression(4L));

        OperationExpression deepExpression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        deepExpression.parts.add(new NumberExpression(2L));
        deepExpression.parts.add(new NumberExpression(3L));


        OperationExpression deepMultiply = new OperationExpression(OperationExpression.OperationSign.MULTIPLY);
        deepMultiply.parts.add(deepExpression);
        deepMultiply.parts.add(new NumberExpression(4L));

        expression.parts.add(deepMultiply);

        NumberExpression responseMsg = target.path("recursive")
                .request("application/xml")
                .post(Entity.entity(expression, "application/xml"))
                .readEntity(NumberExpression.class);
        assertEquals(new Long(25L), responseMsg.getValue());
    }
}
