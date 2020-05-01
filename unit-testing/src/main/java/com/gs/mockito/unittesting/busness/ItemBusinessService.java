package com.gs.mockito.unittesting.busness;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs.mockito.unittesting.dao.daoImpl.ItemRepository;
import com.gs.mockito.unittesting.model.Item;

@Service
public class ItemBusinessService {
	
	@Autowired
	private ItemRepository itemRepository ;
	
	public Item retreiveHardcodedItem() {
		 return new Item(10, "shirt", 110, 100);
	}

	
	public List<Item> retreiveAllItems() {	
		List<Item> items = itemRepository.findAll();
		for (Item item : items) {
			item.setValue(item.getPrice()*item.getQuantity());
		}
		return items;
	}


	public Item save(Item item) {
		Item itemReturn = itemRepository.save(item);
		return itemReturn;
	}


	public Optional<Item>  findById(int itemid) {
		Optional<Item> item = itemRepository.findById(itemid);
		return item;
	}

}
