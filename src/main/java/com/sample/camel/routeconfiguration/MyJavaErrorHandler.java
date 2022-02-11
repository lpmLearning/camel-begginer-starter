package com.sample.camel.routeconfiguration;

import org.apache.camel.builder.RouteConfigurationBuilder;
import org.springframework.stereotype.Component;

// Use @Component to let spring boot discover this class automatic
@Component
public class MyJavaErrorHandler extends RouteConfigurationBuilder {

    @Override
    public void configuration() throws Exception {
        routeConfiguration("javaError")
            .onException(UnsupportedOperationException.class).handled(true)
            .log("Java WARN: ${exception.message}");

        routeConfiguration("javaError2")
            .onException(IllegalArgumentException.class).handled(true)
            .log("Java 2 WARN: ${exception.message}");
    }
}
