import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class TopResultApi_Test {
    private String Username, Passwd;
    int randomNum;

    @Parameters({"uname", "pwd", "baseUrl"})
    @BeforeTest
    public void BefTestMethod(String uname, String pwd, String baseUrl) {
        randomNum = (int) (Math.random() * (500 - 100 + 1) + 100);
        RestAssured.baseURI = baseUrl;
        this.Username = uname;
        this.Passwd = pwd;

        Response rsp = getApiResult(baseUrl);
        JsonPath jpath = new JsonPath(rsp.asString());

        System.out.println("BEFORE TEST The FACT is -" + jpath.getString("fact"));

//        JSONObject jo = new JSONObject(jsonRespAsString);
//        System.out.println("Response Body Size -" + jo.length());
//        maxCount = jo.length();
    }

    @Parameters({"baseUrl"})
    @Test
    public void GetTopResultsApi_Test(String testUrl) {
        Response rsp = getApiResult(testUrl);
        Assert.assertEquals(rsp.statusCode(), 200, "Response Test FAILED");
        JsonPath jpath; //= new JsonPath(rsp.asString());
        jpath = rsp.jsonPath();

        System.out.println("PRETTY Printing JSON object" + jpath.prettyPrint());
//        System.out.println("Inside Test - Response Body Length is -" + jpath.get("length"));
//        System.out.println("Inside Test - The FACT is -" + jpath.get("fact"));


        ResponseBody body = rsp.getBody();
        System.out.println("Response Body is: " + body.asString());

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.

    }

        /*
        Response response = given().auth()
                .preemptive()
                .basic(this.Username, this.Passwd)
                .header("Content-Type", "application/json")
                .get("/getTopResultsApi?count=" + count)
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(count))
                .extract().response();

    }
*/

    @Parameters({"petTestUrl"})
    @Test
    public void postApi_Test(String url) {
        Map<String, Object> catMap = new HashMap<>();
        catMap.put("id", Integer.toString(randomNum));
        catMap.put("name", "Rept Cat");

        Map<String, String> tagMap = new HashMap<>();
        tagMap.put("id", Integer.toString(randomNum));
        tagMap.put("name", "KOALA-456");
        tagMap.put("id", "4561");
        tagMap.put("name", "KANGAROO-4561");

        List<Map<String, String>> tagMapList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Map<String, String> tagMap1 = new HashMap<>();
            tagMap1.put("id", "225" + i);
            tagMap1.put("name", "KAngaroo-456" + i);
            tagMapList.add(tagMap1);
        }

        JSONArray photosArr = new JSONArray();
        photosArr.put("KOALA Img");

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", randomNum);
        requestParams.put("category", (Object) catMap);
        requestParams.put("name", "Kola Test");
        requestParams.put("photoUrls", photosArr);
        requestParams.put("tags", tagMapList);
        requestParams.put("status", "Punching !!!");

        Response rsp = postApiResult(url, requestParams);

        Assert.assertEquals(rsp.statusCode(), 200, "Response Test FAILED");
        JsonPath jpath; //= new JsonPath(rsp.asString());
        jpath = rsp.jsonPath();

        System.out.println("PRETTY Printing JSON object" + jpath.prettyPrint());
//        System.out.println("Inside Test - Response Body Length is -" + jpath.get("length"));
//        System.out.println("Inside Test - The FACT is -" + jpath.get("fact"));

//        ResponseBody body = rsp.getBody();
//        System.out.println("Response Body is: " + body.asString());

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
    }

    @Parameters({"petTestUrl"})
    @Test
    public void putApi_Test(String url) {
        Map<String, Object> catMap = new HashMap<>();
        catMap.put("id", Integer.toString(randomNum));
        catMap.put("name", "Long Snake");

        List<Map<String, String>> tagMapList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Map<String, String> tagMap1 = new HashMap<>();
            tagMap1.put("id", Integer.toString(randomNum) + i);
            tagMap1.put("name", "Elephant-456" + i);
            tagMapList.add(tagMap1);
        }

        JSONArray photosArr = new JSONArray();
        photosArr.put("KOALA Img");

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", randomNum);
        requestParams.put("category", (Object) catMap);
        requestParams.put("name", "Kola Test");
        requestParams.put("photoUrls", photosArr);
        requestParams.put("tags", tagMapList);
        requestParams.put("status", "Punching !!!");

        Response rsp = putApiResult(url, requestParams);

        Assert.assertEquals(rsp.statusCode(), 200, "Response Test FAILED");
        JsonPath jpath;
        jpath = rsp.jsonPath();

        System.out.println("After PUT - Printing JSON object" + jpath.prettyPrint());
    }

    public void validateSchema() {
        get("https://jsonplaceholder.typicode.com/todos/1").then()
                .assertThat().body(matchesJsonSchemaInClasspath("products-schema.json"));
    }

    /*
    private Response getApiResponse(String uname, String pwd, int count) {
        Response rsp = given().auth()
                .preemptive()
                .basic(uname, pwd)
                .header("Content-Type", "application/json")
                .get("/getTopResultsApi?count=" + count)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        return rsp;
    } */

    Response getApiResult(String url) {
        Response rsp = given().baseUri(url)
                .header("Content-Type", "application/json")
                .when()
                .get("/fact")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200 )
                .body(matchesJsonSchemaInClasspath("cat-schema.json"))
                .extract().response();

        System.out.println("Response is " + rsp.path("length"));

        return rsp;
    }

    Response postApiResult(String url, JSONObject requestParams) {
        System.out.println("Request body is - " + requestParams.toString());

        Response rsp = given().baseUri(url)
                .header("Content-Type", "application/json")
                .and()
                .body(requestParams.toString())
                .post("/pet")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .extract().response();

        return rsp;
    }

    Response putApiResult(String url, JSONObject requestParams) {
        System.out.println("Request body is - " + requestParams.toString());

        Response rsp = given().baseUri(url)
                .header("Content-Type", "application/json")
                .and()
                .body(requestParams.toString())
                .put("/pet")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .extract().response();

        return rsp;
    }
}
