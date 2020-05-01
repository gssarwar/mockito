package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gs.mockito.unittesting.dao.SomeBusinessDao;


class SomeBusinessImplMockTest_V3 {

	SomeBusinessImpl serv = new SomeBusinessImpl();
	SomeBusinessDao mockDao = mock(SomeBusinessDao.class);
	
	@BeforeEach
	void beforeEach(){
		serv.setSomeBusinessdao(mockDao);
	}
	
	@Test
	void calculateSumFromDao_Basic() {
		when(mockDao.retriveAllData()).thenReturn(new int[] {1,2,3});
		assertEquals(6, serv.calculateSumFromDao());
	}

	@Test
	void calculateSumFromDao_emptyArray() {

		when(mockDao.retriveAllData()).thenReturn(new int[] {});
		assertEquals(0, serv.calculateSumFromDao());
	}

	@Test
	void calculateSumFromDao_oneValue() {

		when(mockDao.retriveAllData()).thenReturn(new int[] {7});
		assertEquals(7, serv.calculateSumFromDao());
	}

}
