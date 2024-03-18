package de.turing85.spring.camel.load.xml;

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
    Resource routeResource = ResourceHelper.fromString("foo.xml", getResourceXmlString());
    // @formatter:off
    context
        .getCamelContextExtension()
        .getContextPlugin(RoutesLoader.class)
        .loadRoutes(routeResource);
    // @formatter:on
  }

  private static String getResourceXmlString() {
    return """
        <routes xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns="http://camel.apache.org/schema/spring"
                xsi:schemaLocation="
                    http://camel.apache.org/schema/spring
                    http://camel.apache.org/schema/spring/camel-spring.xsd">
            <route id="echo">
                <from uri="platform-http:/hello?httpMethodRestrict=GET"/>
                <log message="called"/>
                <setBody>
                    <constant>Hello, Camel!</constant>
                </setBody>
                <setHeader name="Content-Type">
                    <constant>text/plain</constant>
                </setHeader>
            </route>
        </routes>""";
  }
}
