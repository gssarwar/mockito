package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gs.mockito.unittesting.dao.SomeBusinessDao;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class) 
class SomeBusinessImplMockTest_V4 {

	
	@InjectMocks
	SomeBusinessImpl service ;
	@Mock
	SomeBusinessDao mockDao ;
	
	/*
	 * @BeforeSuite public void setup() { MockitoAnnotations.initMocks(this); }
	 */
	
	
	@Test
	void calculateSumFromDao_Basic() { 
		when(mockDao.retriveAllData()).thenReturn(new int[] {1,2,3});
		assertEquals(6, service.calculateSumFromDao());
	}

	@Test
	void calculateSumFromDao_emptyArray() {
		when(mockDao.retriveAllData()).thenReturn(new int[] {});
		assertEquals(0, service.calculateSumFromDao());
	}

	@Test
	void calculateSumFromDao_oneValue() {
		when(mockDao.retriveAllData()).thenReturn(new int[] {7});
		assertEquals(7, service.calculateSumFromDao());
	}

}
