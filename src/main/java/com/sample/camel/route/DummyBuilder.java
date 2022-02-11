package com.sample.camel.route;

import com.sample.camel.routeconfiguration.MyJavaRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DummyBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:dev?period=2s")
            .setBody(method(DummyBuilder.class, "randomName"))
//            .process(exchange -> System.out.println("Dummy event: " + exchange.getIn().getBody()))
            .log("My Name is ${body}")
            .to("direct:otherName")
            .end();

        from("direct:otherName")
            .setBody(method(DummyBuilder.class, "randomName2"))
//            .process(exchange -> System.out.println("Dummy event: " + exchange.getIn().getBody()))
            .log("My Other Name is ${body}")
            .end();
    }

    public static String randomName() {

        return "MASTER_" + new Random().nextInt(100);

    }

    public static String randomName2() {

        return "SECOND_" + new Random().nextInt(100);

    }
}
