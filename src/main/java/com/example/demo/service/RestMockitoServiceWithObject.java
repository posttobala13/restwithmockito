package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.EmployeeSO;
import com.example.demo.model.EmployeeTO;

public class RestMockitoServiceWithObject {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private HttpEntity<EmployeeSO> httpEntity;

    private HttpHeaders httpHeaders;

    private String resourceURL = null;

    public RestMockitoServiceWithObject(){
	    httpHeaders = new HttpHeaders();
	    httpHeaders.set("Content-Type", "application/json");
    }

    public RestResponse postJSONData(EmployeeTO employeeTO) {
	    resourceURL = env.getProperty("baseURL") + env.getProperty("resourcePath");
	    httpEntity = new HttpEntity<EmployeeSO>(convertIntoSoObject(employeeTO), httpHeaders);
	    return restTemplate.postForObject(resourceURL, httpEntity, RestResponse.class);
    }

	private EmployeeSO convertIntoSoObject(EmployeeTO employeeTO) {
		EmployeeSO employeeSO = new EmployeeSO();
		employeeSO.setEmpId(employeeTO.getEmpId());
		employeeSO.setName(employeeTO.getName());
		return employeeSO;
	} 


}