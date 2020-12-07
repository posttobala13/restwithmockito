package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestMockitoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private HttpEntity<String> httpEntity;

    private HttpHeaders httpHeaders;

    private String resourceURL = null;

    public RestMockitoService(){
	    httpHeaders = new HttpHeaders();
	    httpHeaders.set("Content-Type", "application/json");
    }

    public void postJSONData(List<String> data) {
	    resourceURL = env.getProperty("baseURL") + env.getProperty("resourcePath");
	    httpEntity = new HttpEntity<String>(data.toString(), httpHeaders);
	    restTemplate.postForObject(resourceURL, httpEntity, Void.class);
    } 


}