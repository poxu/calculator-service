package com.calculator;

import com.calculator.beans.OperationBean;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("calculate")
public class CalculatorResource {
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public OperationBean getXml(OperationBean inOp) {
        Calculable calc = null;
        try {
            calc = new ExpressionBuilder(inOp.operation).build();
            return new OperationBean(Double.toString(calc.calculate()));
        }
        catch (Exception e) {
            throw  new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
