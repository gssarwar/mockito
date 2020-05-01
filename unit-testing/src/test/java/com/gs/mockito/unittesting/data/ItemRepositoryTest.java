package com.gs.mockito.unittesting.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gs.mockito.unittesting.dao.daoImpl.ItemRepository;
import com.gs.mockito.unittesting.model.Item;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepository ;
	
	@Test
	public void testFindAll() {
		List<Item> items = itemRepository.findAll();
		assertEquals(4, items.size());
	}
	
	
	

}
