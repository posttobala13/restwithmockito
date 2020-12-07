package com.example.demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.EmployeeSO;
import com.example.demo.model.EmployeeTO;


@RunWith(MockitoJUnitRunner.class)
public class RestMockitoServiceWithObjectTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Environment env;

    @InjectMocks
    private RestMockitoServiceWithObject restMockitoServiceWithObject;

    @Test
    public void test_postJSONData() {
        String baseUrl = "theBaseUrl";
        String resourcePath = "aResourcePath";

        Mockito.when(env.getProperty("baseURL")).thenReturn(baseUrl);
        Mockito.when(env.getProperty("resourcePath")).thenReturn(resourcePath);

        EmployeeTO employeeTO = new EmployeeTO();
        employeeTO.setName("vikas");
        employeeTO.setEmpId("1");
        
        Mockito.when(restTemplate.postForObject(
                Mockito.eq(baseUrl + resourcePath),
                Mockito.any(HttpEntity.class),
                Mockito.eq(RestResponse.class))).thenAnswer(new Answer() {

					@Override
					public RestResponse answer(InvocationOnMock arg0) throws Throwable {
						HttpEntity<EmployeeSO> httpEntity = (HttpEntity<EmployeeSO>)arg0.getArguments()[1];
						
						EmployeeSO employeeSO = httpEntity.getBody();
						Assert.assertEquals("1", employeeSO.getEmpId());;
						Assert.assertEquals("vikas", employeeSO.getName());;
						
						return buildRestResponse();
					}
                	
                });

        RestResponse response = restMockitoServiceWithObject.postJSONData(employeeTO);
        Assert.assertEquals("ok", response.getResponse());;
        Mockito.verify(restTemplate).postForObject(
                Mockito.eq(baseUrl + resourcePath),
                Mockito.any(HttpEntity.class),
                Mockito.eq(RestResponse.class)
        );

    }

	private RestResponse buildRestResponse() {
		RestResponse response = new RestResponse();
		response.setResponse("ok");
		return response;
	}
}