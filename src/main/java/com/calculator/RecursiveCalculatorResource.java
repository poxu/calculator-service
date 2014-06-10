package com.calculator;

import com.calculator.beans.NumberExpression;
import com.calculator.beans.OperationExpression;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("recursive")
public class RecursiveCalculatorResource {
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public NumberExpression evaluateExpression(OperationExpression inExp) {
        return new NumberExpression(inExp.getValue());
    }
}
