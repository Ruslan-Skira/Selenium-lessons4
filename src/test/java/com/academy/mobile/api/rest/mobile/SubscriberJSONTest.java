package com.academy.mobile.api.rest.mobile;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SubscriberJSONTest {
    private static final Logger LOG = LogManager.getLogger(SubscriberXmlTests.class);

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "http://localhost/rest/xml";
        RestAssured.port = 8080;
    }

    @Ignore
    @Test
    public void testGet() {
        LOG.info("API-> test subscribers get by id");
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/subscribers/1");
        ResponseBody body = response.body();
        XmlPath xmlPath = body.xmlPath();
        int  id = xmlPath.getInt("subscriber.id");
        String  name = xmlPath.getString("subscriber.firstName");
        int code = response.statusCode();

        Assert.assertEquals(code, 200);
        Assert.assertEquals(id, 1);
        Assert.assertEquals(name, name);
    }

    @Test
    public void testGetDsl() {
        LOG.info("API-> test subscribers get by id");
        given().log().all()
                .when().get("/subscribers/{id}", 1)
                .then().assertThat()
                .body("subscriber.id", equalTo("1"))
                .body("subscriber.firstName", equalTo("Иван"))
                .and().statusCode(200);
    }

    @Ignore
    @Test
    public void testGetAll() {
        LOG.info("API-> test subscribers get All");
        given().log().all()
                .when().get("/subscribers")
                .then().assertThat()
                .body("subscribers.children().size()", equalTo(4))
                .and()
                .body("subscribers.subscriber[0].id", equalTo("1"))
                .and()
                .statusCode(200);
    }

    @Ignore
    @Test
    public void testPost() {
        // TODO if exists
        JSONObject json = new JSONObject();
        json.put("firstName", "Vasya"); // Cast
        json.put("lastName", "Pupkin");
        json.put("age", "25");
        json.put("gender", "m");

        given().log().all()
                .header("Content-Type", "application/json")
//                .body("{\"firstName\":\"Olegjan\",\"lastName\":\"Afanasiev\",\"age\":\"36\",\"gender\":\"m\"}")
                .body(json.toJSONString())
                .post("/subscribers")
                .then().assertThat()
                .header("Location", containsString("http://localhost:8080/rest/subscribers/"))
                .statusCode(201);
    }

    @Ignore
    @Test
    public void testUpdate() {
        JSONObject json = new JSONObject();
        json.put("id", "28"); // Cast
        json.put("firstName", "Vasya"); // Cast
        json.put("lastName", "Pupkin");
        json.put("age", "25");
        json.put("gender", "m");
        given().log().all()
                .header("Content-Type", "application/json")
//                .body("{\"id\":\"28\",\"firstName\":\"Olegjan\",\"lastName\":\"Afanasiev\",\"gender\":\"m\",\"age\":\"36\"}")
                .body(json.toJSONString())
                .put("/subscribers/28")
                .then().assertThat()
                .body("id", equalTo("28"))
                .and()
                .body("lastName", equalTo("Pupkin"))
                .statusCode(200);
    }

    @Ignore
    @Test
    public void testDelete() {
        given().log().all()
                .delete("/subscribers/28")
                .then().assertThat()
                .statusCode(200);
    }
}
