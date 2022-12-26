package com.exchange.exchange_portal.parser;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(namespace = "http://www.gesmes.org/xml/2002-08-01", name = "Envelope")
public final class Envelope {

    @XmlElement(name = "subject", namespace = "http://www.gesmes.org/xml/2002-08-01")
    private String subject;

    @XmlElement(name = "Sender", namespace = "http://www.gesmes.org/xml/2002-08-01")

    private Sender sender;

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    private Cube cube;

}