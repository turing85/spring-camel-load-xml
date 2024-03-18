package de.turing85.spring.camel.load.xml;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Resource;
import org.apache.camel.spi.RoutesLoader;
import org.apache.camel.support.ResourceHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class XmlLoader extends RouteBuilder {
  private final CamelContext context;

  @Override
  public void configure() throws Exception {
    String fileName = "echoRoute.xml";
    // @formatter:off
    URI echoRouteUrl = Optional.of(getClass())
        .map(Class::getClassLoader)
        .map(loader -> loader.getResource(fileName))
        .orElseThrow()
        .toURI();
    // @formatter:on
    Path echoRoutePath = Path.of(echoRouteUrl);
    Resource routeResource = ResourceHelper.fromBytes(fileName, Files.readAllBytes(echoRoutePath));
    // @formatter:off
    context
        .getCamelContextExtension()
        .getContextPlugin(RoutesLoader.class)
        .loadRoutes(routeResource);
    // @formatter:on
  }
}
