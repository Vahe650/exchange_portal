package com.exchange.exchange_portal.parser;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@XmlRootElement(namespace = "http://www.gesmes.org/xml/2002-08-01", name = "Sender")
public class Sender {

    @XmlElement(name = "name", namespace = "http://www.gesmes.org/xml/2002-08-01")
    private String name;


}