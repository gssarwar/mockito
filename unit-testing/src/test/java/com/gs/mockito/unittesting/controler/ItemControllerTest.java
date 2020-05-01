package com.gs.mockito.unittesting.controler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.mockito.unittesting.busness.ItemBusinessService;
import com.gs.mockito.unittesting.model.Item;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc; 

	@MockBean
	private ItemBusinessService itemBusinessService;

	/*
	 * @Autowired private WebApplicationContext wac;
	 * 
	 * private RestTemplate restTemplate; private int userId = 5; private String
	 * jsonDateFormatPattern = "yyyy-MM-dd HH:mm:ss"; private MockRestServiceServer
	 * mockServer; private MockMvc mockMvc1; private MediaType
	 * applicationJsonMediaType = new
	 * MediaType(MediaType.APPLICATION_JSON.getType(),
	 * MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	 * 
	 * private MediaType vndErrorMediaType =
	 * MediaType.parseMediaType("application/vnd.error");
	 */
	/*
	 * @BeforeEach public void setup() {
	 * List<org.springframework.http.converter.HttpMessageConverter<?>> converters =
	 * new ArrayList<HttpMessageConverter<?>>(); converters.add(new
	 * StringHttpMessageConverter()); converters.add(new
	 * MappingJackson2HttpMessageConverter());
	 * 
	 * this.restTemplate = new RestTemplate();
	 * this.restTemplate.setMessageConverters(converters);
	 * 
	 * this.mockServer = MockRestServiceServer.createServer(this.restTemplate);
	 * this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); }
	 */

	@Test
	void getItemList_basic() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/itemList").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"ball\",\"price\":5,\"quantity\":10}")).andReturn();
		// assertEquals("hello world", result.getResponse().getContentAsString());
	}

	@Test
	void itemFromBusinessService_basic() throws Exception {

		when(itemBusinessService.retreiveHardcodedItem()).thenReturn(new Item(10, "shirt", 41, 10));
		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{id:10,name:shirt,price:41,quantity:10}")).andReturn();
		// assertEquals("hello world", result.getResponse().getContentAsString());
	}

	@Test
	void retriveAllItem_basic() throws Exception {

		when(itemBusinessService.retreiveAllItems())
				.thenReturn(Arrays.asList(new Item(10, "item1", 10, 10), new Item(20, "item2", 20, 20)));
		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-database").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content()
						.json("[{id:10,name:item1,price:10,quantity:10},{id:20,name:item2,price:20,quantity:20}]"))
				.andReturn();
		// assertEquals("hello world", result.getResponse().getContentAsString());
	}


	@Test
	public void createItem() throws Exception {
		Item item = new Item(10, "shirt", 41, 10);
		String json = "{\"id\":10,\"name\":\"shirt\",\"price\":41,\"quantity\":10}";
		when(itemBusinessService.save(item)).thenReturn(item);
		RequestBuilder request = MockMvcRequestBuilders.post("/items").accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);

		HttpHeaders httpHeaders = new HttpHeaders();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(item.getId())
				.toUri();
		httpHeaders.setLocation(location);
//	  MockMvcRequestBuilders.request("/items", uri)

		MvcResult result = mockMvc.perform(request).andExpect(status().isCreated())

				.andReturn();
	}
	
	@Test
	public void test_save() throws Exception {

		Item item = new Item(30, "Item4", 200, 100);
		String jsonObject = "{\"id\":30,\"name\":\"Item4\",\"price\":200,\"quantity\":100}";
		when(itemBusinessService.save(item)).thenReturn(item);
		MvcResult mvcResult = mockMvc
				.perform(post("/items")
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonObject)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("/item")))
				.andReturn();
		
		String locationUri = mvcResult.getResponse().getHeader("location");
		System.out.println(locationUri);
		assertEquals(locationUri, "http://localhost/items/item/30");
		
		
	}


}
