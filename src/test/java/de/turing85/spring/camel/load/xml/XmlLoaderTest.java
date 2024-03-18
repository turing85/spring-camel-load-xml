package de.turing85.spring.camel.load.xml;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class XmlLoaderTest {
  @BeforeAll
  static void setup(@LocalServerPort int port) {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  @AfterAll
  static void tearDown() {
    RestAssured.baseURI = "";
    RestAssured.port = 0;
  }

  @Test
  void testGet() {
    // @formatter:off
    RestAssured
        .when().get("/hello")
        .then()
            .statusCode(is(HttpStatus.OK.value()))
            .body(is("Hello, Camel!"));
    // @formatter:on
  }
}
