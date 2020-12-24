package ru.alex.testwork.camelroutes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("timer:timer?period=1000&repeatCount=1")
//                .to("log:log1?showAll=true")
        restConfiguration().component("servlet")
                .port("8080")
                .bindingMode(RestBindingMode.json)
        ;

        rest("/api")
                .get("/run")
                .outType(String.class)
                .responseMessage()
                .code(204)
                .message("Running...")
                .endResponseMessage()
                .to("log:log2?showAll=true")
                .to("direct:start")
        ;

//        from("direct:start")
//                .pollEnrich("file:inbox?noop=true")
//                .to("log:log2?showAll=true")
//                .filter(header("CamelFileName").endsWith(".xml"))
//                .to("log:log3?showAll=true")
//        ;
    }
}
