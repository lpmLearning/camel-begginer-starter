package com.sample.camel.routeconfiguration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyJavaRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        onException(IllegalArgumentException.class).handled(true)
//            .log("Java WARN from CONSUMER: ${exception.message}");

        from("timer:java?period=2s")
            // refer to the route configuration by the id to use for this route
            .routeConfigurationId("javaError,javaError2,reportError")
            .setBody(method(MyJavaRouteBuilder.class, "randomNumber"))
            .log("Random number ${body}")
            .filter(simple("${body} < 30"))
            .throwException(new IllegalArgumentException("The number is too low"));
    }

    public static int randomNumber() {
        return new Random().nextInt(100);
    }
}
