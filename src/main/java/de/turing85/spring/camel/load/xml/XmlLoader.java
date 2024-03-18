package de.turing85.spring.camel.load.xml;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Resource;
import org.apache.camel.spi.ResourceLoader;
import org.apache.camel.spi.RoutesLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class XmlLoader extends RouteBuilder {
  private final CamelContext context;

  @Override
  public void configure() throws Exception {
    // @formatter:off
    Resource routeResource = context
        .getCamelContextExtension()
        .getContextPlugin(ResourceLoader.class)
        .resolveResource("classpath:echoRoute.xml");
    context
        .getCamelContextExtension()
        .getContextPlugin(RoutesLoader.class)
        .loadRoutes(routeResource);
    // @formatter:on
  }
}
