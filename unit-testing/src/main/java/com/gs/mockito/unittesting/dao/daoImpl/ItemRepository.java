package com.gs.mockito.unittesting.dao.daoImpl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.mockito.unittesting.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
