package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SomeBusinessImplTest {

	@Test
	void calculateSum() {
		SomeBusinessImpl serv= new SomeBusinessImpl();
		int actualResult = serv.calculateSum(new int[]{1,2,3});
		int expextedResurlt = 6 ;
		assertEquals(expextedResurlt, actualResult);
	}
	
	@Test
	void calculateSum_emptyArray() {
		SomeBusinessImpl serv= new SomeBusinessImpl();
		int actualResult = serv.calculateSum(new int[]{});
		int expextedResurlt = 0 ;
		assertEquals(expextedResurlt, actualResult);
	}
	@Test
	void calculateSum_oneValue() {
		SomeBusinessImpl serv= new SomeBusinessImpl();
		int actualResult = serv.calculateSum(new int[]{7});
		int expextedResurlt = 7 ;
		assertEquals(expextedResurlt, actualResult);
	}


}
