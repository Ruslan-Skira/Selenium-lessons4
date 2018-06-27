package com.academy.mobile.api.soap.mobile;

import com.academy.mobile.api.rest.mobile.SubscriberXmlTests;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class SubscriberWSTests {
    private static final Logger LOG = LogManager.getLogger(SubscriberXmlTests.class);

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));
    }

    @Test(dataProvider = "getSubscriber")
    public void testGetSubscriber(String xmlReq) {
        Response resp = given().config(config).log().all()
                .request()
                .body(xmlReq)
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "findSoapAction")
                .header("DataEncoding", "UTF-8")
                .when()
                    .post("/soap")
                .then()
                    .assertThat()
                    .statusCode(200).extract().response();


        System.out.println(resp.asString());
    }

    @DataProvider(name="getSubscriber")
    public Object[] subscriberDataProvider() {
        String xmlReq = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.mobile.academy.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:getSubscriber>\n" +
                "         <arg0>3</arg0>\n" +
                "      </soap:getSubscriber>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return new Object[] {
                xmlReq
        };
    }
}
