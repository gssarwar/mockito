package com.gs.mockito.unittesting.controler;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemControllerIT {

	@Autowired 
	TestRestTemplate testRestTemplate ;
	
	@Test
	public void contextLoads() throws JSONException {
		String response = this.testRestTemplate.getForObject("/item-from-database", String.class);
		JSONAssert.assertEquals("[{id:10},{id:20},{id:30},{id:40}]", response, false);
		
	}

}
