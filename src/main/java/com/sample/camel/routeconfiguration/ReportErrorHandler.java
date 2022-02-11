package com.sample.camel.routeconfiguration;

import org.apache.camel.builder.RouteConfigurationBuilder;
import org.springframework.stereotype.Component;

// Use @Component to let spring boot discover this class automatic
@Component
public class ReportErrorHandler extends RouteConfigurationBuilder {

    @Override
    public void configuration() throws Exception {
        routeConfiguration("reportError")
            .onException(NullPointerException.class).handled(true)
            .log("REPORT ERROR: ${exception.message}");
    }
}
