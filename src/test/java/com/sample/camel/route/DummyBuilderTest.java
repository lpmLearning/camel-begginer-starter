package com.sample.camel.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyBuilderTest extends CamelTestSupport {

    @EndpointInject("mock:direct:end")
    protected MockEndpoint endEndpoint;

    @Produce("direct:dummyConsumer")
    private ProducerTemplate producerTemplate;

    @Test
    void shouldSendMessage() throws InterruptedException {

        endEndpoint.expectedMessageCount(0);
        producerTemplate.sendBody("HELLO ALL");

        endEndpoint.expectedMessageCount(1);
        endEndpoint.assertIsSatisfied();
    }
}