package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
@RunWith(MockitoJUnitRunner.class)
public class RestMockitoServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Environment env;

    @InjectMocks
    private RestMockitoService restMockitoService;

    @Test
    public void test_postJSONData() {
        String baseUrl = "theBaseUrl";
        String resourcePath = "aResourcePath";

        Mockito.when(env.getProperty("baseURL")).thenReturn(baseUrl);
        Mockito.when(env.getProperty("resourcePath")).thenReturn(resourcePath);

        List<String> payload = new ArrayList<>();
        
       Mockito.when(restTemplate.postForObject(
                Mockito.eq(baseUrl + resourcePath),
                Mockito.any(HttpEntity.class),
                Mockito.eq(Void.class))
        ).thenReturn(buidlVoid());


        restMockitoService.postJSONData(payload);
        
        Mockito.verify(restTemplate).postForObject(
                Mockito.eq(baseUrl + resourcePath),
                Mockito.any(HttpEntity.class),
                Mockito.eq(Void.class)
        );

    }

	private Void buidlVoid() {
		return null;
	}
}