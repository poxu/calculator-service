package com.calculator.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by riptor on 07.06.2014.
 */
@XmlRootElement
public class OperationBean {
    @XmlAttribute
    public String operation;

    public OperationBean(String operation) {
        this.operation = operation;
    }

    public OperationBean() {
    }
}

