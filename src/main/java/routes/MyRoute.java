package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;


@Component
public class MyRoute extends RouteBuilder {

    DefaultCamelContext

    @Override
    public void configure() throws Exception {
        from("timer:hello?period=1000").routeId("hello")
                .setBody(simple("JOPA......"))
                .to("stream:out");
    }
}
