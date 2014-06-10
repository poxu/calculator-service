package com.calculator.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OperationExpression extends NumberExpression {
    public enum OperationSign {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }
    @XmlAttribute
    public OperationSign operation;
    @XmlElement(name = "members")
    public List<NumberExpression> parts = new ArrayList<NumberExpression>();


    public OperationExpression(OperationSign operation) {
        this.operation = operation;
    }

    public OperationExpression() {
    }

    @Override
    public Long getValue() {
        if (parts.isEmpty()) {
            throw new IllegalArgumentException("Operation has no operands");
        }

        Long result = parts.get(0).getValue();

        if (parts.size() < 2)
            return result;

        for (int i = 1;i<parts.size();i++) {
            switch (operation) {
                case PLUS:
                    result += parts.get(i).getValue();
                    break;
                case MINUS:
                    result -= parts.get(i).getValue();
                    break;
                case MULTIPLY:
                    result *= parts.get(i).getValue();
                    break;
                case DIVIDE:
                    result /= parts.get(i).getValue();
                    break;
                default:
                    throw new IllegalArgumentException("Operation is not supported");
            }
        }

        return result;
    }
}
