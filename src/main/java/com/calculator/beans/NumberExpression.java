package com.calculator.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NumberExpression {
    @XmlAttribute
    public Long value;

    public NumberExpression() {
    }

    public NumberExpression(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
