package com.firstutility.talktalk.linecharcteristics;

import com.firstutility.talktalk.linecharateristics.Application;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.*;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({
        "server.port:9000",
        "LineCharacteristicsWS.serviceEndpoint:http://localhost:1080/LineCharacteristicsWSV6/LineCharacteristicsWS.asmx",
        "LineCharacteristicsWS.soapAction:testEndpoint"
})
public class ApplicationIntegrationTest {

    RestTemplate template = new TestRestTemplate();

    private ClientAndProxy proxy;

    private ClientAndServer mockServer;

    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
    }

    @After
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void testLineCharacteristicsSuccessResponse() throws Exception {

        String responseBody = new String(readAllBytes(get(
                System.getProperty("user.dir") + "/src/main/resources/responses/responseSuccess.xml")));

        final String telephoneNumber = "01630656588";
        final String postCode = "TF93DJ";

        mockServer.when(
                request()
                        .withMethod("POST")
                        .withPath("/LineCharacteristicsWSV6/LineCharacteristicsWS.asmx")
                        .withHeader(
                                header("SOAPAction", "\"testEndpoint\"")))
                        //.withBody(regex(telephoneNumber)))
                .respond(response()
                        .withStatusCode(200)
                        .withHeaders(
                                header("Content-Type", "text/xml; charset=utf-8"),
                                header("Content-Length", responseBody.length()))
                        .withBody(responseBody)
                );

        JSONObject responseObj = new JSONObject(template.getForObject(
                buildTestUrl(telephoneNumber, postCode),
                String.class));

        assertNotNull(responseObj.getJSONObject("getLineCharacteristicsResult"));
        JSONObject getLineCharacteristicsResult = responseObj.getJSONObject("getLineCharacteristicsResult");

        // Check Status
        assertNotNull(getLineCharacteristicsResult.getJSONObject("status"));
        JSONObject status = getLineCharacteristicsResult.getJSONObject("status");
        Object hasErrors = status.get("hasErrors");
        assertFalse((boolean) hasErrors);

        // ad then test the rest of the JSON response
        assertNotNull(getLineCharacteristicsResult.getJSONObject("responseDetails"));
        JSONObject responseDetails = getLineCharacteristicsResult.getJSONObject("responseDetails");

        assertNotNull(responseDetails.getJSONArray("characteristicsBase"));
        JSONArray characteristicsBase = responseDetails.getJSONArray("characteristicsBase");

        // etc, etc, etc etc.
    }

    @Test
    public void testLineCharacteristicsVirginMediaResponse() throws Exception {

        String responseBody = new String(readAllBytes(get(
                System.getProperty("user.dir") + "/src/main/resources/responses/virginMediaResponse.xml")));

        mockServer.when(
                request()
                        .withMethod("POST")
                        .withPath("/LineCharacteristicsWSV6/LineCharacteristicsWS.asmx")
                        .withHeader(
                                header("SOAPAction", "\"testEndpoint\"")))
                .respond(response()
                        .withStatusCode(200)
                        .withHeaders(
                                header("Content-Type", "text/xml; charset=utf-8"),
                                header("Content-Length", responseBody.length()))
                        .withBody(responseBody)
                );

        JSONObject responseObj = new JSONObject(template.getForObject(
                "http://localhost:9000/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=01827708166&postCode=TF93DJ",
                String.class));

        assertNotNull(responseObj.getJSONObject("getLineCharacteristicsResult"));
        JSONObject getLineCharacteristicsResult = responseObj.getJSONObject("getLineCharacteristicsResult");

        // Check Status
        assertNotNull(getLineCharacteristicsResult.getJSONObject("status"));
        JSONObject status = getLineCharacteristicsResult.getJSONObject("status");
        Object hasErrors = status.get("hasErrors");
        assertTrue((boolean) hasErrors);

        // ad then test the rest of the JSON response
        assertNotNull(status.getJSONObject("errors"));
        JSONObject errors = status.getJSONObject("errors");
        JSONObject error = errors.getJSONArray("error").getJSONObject(0);

        assertEquals("Porting cannot be performed for Number Range Holder: Virgin Media",
                error.get("errorMessage"));
        assertEquals("REQUEST_ERROR",
                error.get("errorCategory"));
    }


    @Test
    public void testLineCharacteristicsUnknownNumberResponse() throws Exception {

        String responseBody = new String(readAllBytes(get(
                System.getProperty("user.dir") + "/src/main/resources/responses/unknownResponse.xml")));

        mockServer.when(
                request()
                        .withMethod("POST")
                        .withPath("/LineCharacteristicsWSV6/LineCharacteristicsWS.asmx")
                        .withHeader(
                                header("SOAPAction", "\"testEndpoint\"")))
                .respond(response()
                        .withStatusCode(200)
                        .withHeaders(
                                header("Content-Type", "text/xml; charset=utf-8"),
                                header("Content-Length", responseBody.length()))
                        .withBody(responseBody)
                );

        JSONObject responseObj = new JSONObject(template.getForObject(
                "http://localhost:9000/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=01234567890&postCode=TF93DJ",
                String.class));

        assertNotNull(responseObj.getJSONObject("getLineCharacteristicsResult"));
        JSONObject getLineCharacteristicsResult = responseObj.getJSONObject("getLineCharacteristicsResult");

        // Check Status
        assertNotNull(getLineCharacteristicsResult.getJSONObject("status"));
        JSONObject status = getLineCharacteristicsResult.getJSONObject("status");
        Object hasErrors = status.get("hasErrors");
        assertTrue((boolean) hasErrors);

        // ad then test the rest of the JSON response
        assertNotNull(status.getJSONObject("errors"));
        JSONObject errors = status.getJSONObject("errors");
        JSONObject error = errors.getJSONArray("error").getJSONObject(0);

        assertEquals("Porting cannot be performed for Number Range Holder: Unknown",
                error.get("errorMessage"));
        assertEquals("REQUEST_ERROR",
                error.get("errorCategory"));
    }

    private String buildTestUrl(final String telephoneNumber, final String postCode) {
        return "http://localhost:9000/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=" +
                telephoneNumber +
                "0&postCode=" +
                postCode;
    }
}
