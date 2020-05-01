package com.gs.mockito.unittesting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
@Entity
public class Item {
	


	@Id
	private int id ;
	private String name ;
	private int price ;
	private int quantity ;
	
	@Transient
	private int value ;
	
	public Item(int id, String name, int price, int quantity) {
		
		this.id=id;
		this.name = name ;
		this.price = price ;
		this.quantity = quantity ;
	}
	
	
	
	

}
