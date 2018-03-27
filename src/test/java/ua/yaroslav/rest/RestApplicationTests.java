package ua.yaroslav.rest;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.commons.lang3.StringUtils;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApplicationTests {
    private static final String APPID = "test_api_id";
    private static final String LONDON = "london";
    private static final String KYIV = "kyiv";
    private static final String Q_PARAM = "q";
    private static final String APPID_PARAM = "appid";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ApplicationContext context;
    @Rule
    public WireMockClassRule wireMockRule = new WireMockClassRule(wireMockConfig().port(9999));

    @Test
    public void whenRequestWeatherInLondonShouldReturnMockData() throws IOException {
        this.wireMockRule.stubFor(get(urlMatching("/wm.*"))
                .withQueryParam(Q_PARAM, matching(LONDON))
                .withQueryParam(APPID_PARAM, matching(APPID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(getJSON("mapping/london.json"))));

        ResponseEntity<String> london = restTemplate.getForEntity("/weather/" + LONDON, String.class);

        assertEquals(200, london.getStatusCode().value());
        assertTrue(StringUtils.containsAny(london.getBody(), "weather"));
        assertTrue(StringUtils.containsAny(london.getBody(), "London"));
    }

    @Test
    public void whenRequestWeatherInKyivShouldReturnMockData() throws IOException {
        this.wireMockRule.stubFor(get(urlMatching("/wm.*"))
                .withQueryParam(Q_PARAM, matching(KYIV))
                .withQueryParam(APPID_PARAM, matching(APPID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(getJSON("mapping/kyiv.json"))));

        ResponseEntity<String> kyiv = restTemplate.getForEntity("/weather/" + KYIV, String.class);

        assertEquals(200, kyiv.getStatusCode().value());
        assertTrue(StringUtils.containsAny(kyiv.getBody(), "weather"));
        assertTrue(StringUtils.containsAny(kyiv.getBody(), "Kyiv"));
    }

    private String getJSON(String path) throws IOException {
        Resource r = context.getResource("classpath:" + path);
        return new String(Files.readAllBytes(r.getFile().toPath()));
    }
}