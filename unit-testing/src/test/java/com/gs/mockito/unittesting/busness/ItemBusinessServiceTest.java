package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gs.mockito.unittesting.dao.daoImpl.ItemRepository;
import com.gs.mockito.unittesting.model.Item;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class) 
class ItemBusinessServiceTest {

	
	@InjectMocks
	ItemBusinessService ItemBusinessService ;
	
	@Mock
	ItemRepository itemRepository ;
	
	/*
	 * @BeforeSuite public void setup() { MockitoAnnotations.initMocks(this); }
	 */
	
	
	@Test
	void retriveAllItem_Basic() { 
		when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item(10,"item1",10,10),new Item(20,"item2",20,20)));
		List<Item> retreiveAllItems = ItemBusinessService.retreiveAllItems();
		assertEquals(100, retreiveAllItems.get(0).getValue());
		assertEquals(400, retreiveAllItems.get(1).getValue());
	}

}
