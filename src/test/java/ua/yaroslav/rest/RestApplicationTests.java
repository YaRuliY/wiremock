package ua.yaroslav.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ua.yaroslav.rest.dto.WeatherResponseDto;
import ua.yaroslav.rest.exception.WeatherException;
import wiremock.org.apache.commons.lang3.StringUtils;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.*;


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
    @Autowired
    private ObjectMapper mapper;

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

        ResponseEntity<WeatherResponseDto> london = restTemplate.getForEntity("/weather/" + LONDON, WeatherResponseDto.class);
        WeatherResponseDto dto = london.getBody();

        assertEquals(200, london.getStatusCode().value());
        assertNotNull(dto.getDescription());
        assertNotNull(dto.getName());
        assertTrue(dto.getWindSpeed() > 0);
        assertTrue(dto.getPressure() > 0);
        assertTrue(StringUtils.equals(dto.getName(), "Лондон"));
    }

    @Test
    public void whenRequestWeatherInKyivShouldReturnMockData() throws IOException {
        this.wireMockRule.stubFor(get(urlMatching("/wm.*"))
                .withQueryParam(Q_PARAM, matching(KYIV))
                .withQueryParam(APPID_PARAM, matching(APPID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(getJSON("mapping/kyiv.json"))));

        ResponseEntity<WeatherResponseDto> kyiv = restTemplate.getForEntity("/weather/" + KYIV, WeatherResponseDto.class);
        WeatherResponseDto dto = kyiv.getBody();

        System.out.println(dto);
        assertEquals(200, kyiv.getStatusCode().value());
        assertNotNull(dto.getDescription());
        assertNotNull(dto.getName());
        assertTrue(dto.getWindSpeed() > 0);
        assertTrue(dto.getPressure() > 0);
        assertTrue(StringUtils.equals(dto.getName(), "Киев"));
    }

    @Test
    public void whenRequestWeatherInCityWithWrongNameShouldReturnWeatherException() throws IOException {
        this.wireMockRule.stubFor(get(urlMatching("/wm.*"))
                .withQueryParam(Q_PARAM, matching("NotKyiv"))
                .withQueryParam(APPID_PARAM, matching(APPID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody(getJSON("mapping/error.json"))));

        ResponseEntity<String> response = restTemplate.getForEntity("/weather/" + "NotKyiv", String.class);
        WeatherException weatherException = mapper.readValue(response.getBody(), WeatherException.class);

        assertEquals("city not found", weatherException.getMessage());
        assertEquals(404, weatherException.getCode());
        assertEquals(400, response.getStatusCode().value());
    }

    private String getJSON(String path) throws IOException {
        Resource r = context.getResource("classpath:" + path);
        return new String(Files.readAllBytes(r.getFile().toPath()));
    }
}