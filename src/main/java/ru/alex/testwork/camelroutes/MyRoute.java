package ru.alex.testwork.camelroutes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("timer:timer?period=1000").routeId("hello")
//                .to("log:out>>>>>>>>>>>>>?showAll=true")
                from("file:outbox?noop=true")
                .to("log:file>>")
                .convertBodyTo(String.class)
                        .to("log:file>>")
                .setBody().xpath("//bean/camelcontext/@id", String.class)
                .to("log:xml>>");
    }
}
