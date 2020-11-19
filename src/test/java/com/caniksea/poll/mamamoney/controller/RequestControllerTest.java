package com.caniksea.poll.mamamoney.controller;

import com.caniksea.poll.mamamoney.factory.UssdRequestFactory;
import com.caniksea.poll.mamamoney.model.UssdRequest;
import com.caniksea.poll.mamamoney.model.UssdResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RequestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private String baseURL;

    @Before
    public void setUp() {
        this.baseURL = "http://localhost:8080/ussd";
    }

    @Test
    public void request() {
        UssdRequest request = UssdRequestFactory.build("0785662514", "000.000.002", "1");
        ResponseEntity<UssdResponse> response = testRestTemplate.postForEntity(this.baseURL, request, UssdResponse.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}