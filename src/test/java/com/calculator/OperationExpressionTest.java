package com.calculator;

import com.calculator.beans.NumberExpression;
import com.calculator.beans.OperationExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class OperationExpressionTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimplePlus() {
        OperationExpression expression;
        expression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        expression.parts.add(new NumberExpression(1L));
        expression.parts.add(new NumberExpression(4L));

        assertEquals(new Long(5L), expression.getValue());
    }

    @Test
    public void testRecursivePlus() {
        OperationExpression expression;
        expression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        expression.parts.add(new NumberExpression(1L));
        expression.parts.add(new NumberExpression(4L));

        OperationExpression deepExpression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        deepExpression.parts.add(new NumberExpression(2L));
        deepExpression.parts.add(new NumberExpression(3L));

        expression.parts.add(deepExpression);

        assertEquals(new Long(10L), expression.getValue());
    }


    @Test
    public void testRecursiveCombined() {
        OperationExpression expression;
        expression = new OperationExpression(OperationExpression.OperationSign.PLUS);
        expression.parts.add(new NumberExpression(1L));
        expression.parts.add(new NumberExpression(4L));

        OperationExpression deepExpression = new OperationExpression(OperationExpression.OperationSign.MULTIPLY);
        deepExpression.parts.add(new NumberExpression(2L));
        deepExpression.parts.add(new NumberExpression(3L));

        expression.parts.add(deepExpression);

        assertEquals(new Long(11L), expression.getValue());
    }

    @Test
    public void testComplexExpression() {
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

        assertEquals(new Long(25L), expression.getValue());
    }
}
