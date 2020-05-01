package com.gs.mockito.unittesting.controler;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.gs.mockito.unittesting.busness.ItemBusinessService;
import com.gs.mockito.unittesting.model.Item;

@RestController
public class ItemController {
	
	
	@Autowired
	private ItemBusinessService businessService;

	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1, "Ball", 10, 100); 
	}
	
	@GetMapping("/item-from-business-service")
	public Item itemFromBusinessService() {
	
		Item item = businessService.retreiveHardcodedItem();
		
		return item;
	} 
	
	
	@GetMapping("/item-from-database")
	public List<Item> itemFromdataBase() {
		List<Item> items = businessService.retreiveAllItems();
		return items;
	}
	@GetMapping("/items/item/{itemid}")
	public Optional<Item>  findItemById(@PathVariable int itemid) {
		Optional<Item>  item =businessService.findById(itemid);
		return item; 
	}
	
	
	

	
	
	@PostMapping("/items_1")
	public ResponseEntity<Object> save(@RequestBody Item item){
		Item savedItem = businessService.save(item);
		 HttpHeaders httpHeaders = new HttpHeaders();
		  URI location = ServletUriComponentsBuilder.
		  fromCurrentRequest().path("{id}"). buildAndExpand(savedItem.getId()).toUri();
		  httpHeaders.setLocation(location);
		  return new ResponseEntity<>(savedItem, httpHeaders, HttpStatus.CREATED);
		 
	
	}
	
	@PostMapping("/items1")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item savedItem = businessService.save(item);
        HttpHeaders httpHeaders = new HttpHeaders();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path("{id}").buildAndExpand(savedItem.getId());
        httpHeaders.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(savedItem, httpHeaders, HttpStatus.CREATED);
    }
	
	@PostMapping("/items2")
	ResponseEntity<Item> addItem2(@RequestBody Item item) {
		Item savedItem = businessService.save(item);

		URI uriOfNewResource = 
				ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(savedItem.getId())
				.toUri();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uriOfNewResource);

		return new ResponseEntity<>(savedItem, httpHeaders, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/items")
	public ResponseEntity<Object> save1(@RequestBody Item item){
		Item savedItem = businessService.save(item);
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/item/{id}")
		.buildAndExpand(savedItem.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	/*
	 * @GetMapping("/all-items-from-database") public List<Item> retrieveAllItems()
	 * { return businessService.retrieveAllItems(); }
	 */
	//mockito 
	//assertj
	//hamcrest
}
