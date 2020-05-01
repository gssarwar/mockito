package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gs.mockito.unittesting.dao.SomeBusinessDao;

class SomeBusinessdaoImplStub implements SomeBusinessDao {
	@Override
	public int[] retriveAllData() {
		return new int[] { 1, 2, 3 };
	}

}

class SomeBusinessdaoImplSingleValueStub implements SomeBusinessDao {
	@Override
	public int[] retriveAllData() {
		return new int[] { 7 };
	}

}

class SomeBusinessdaoImplEmptyStub implements SomeBusinessDao {
	@Override
	public int[] retriveAllData() {
		return new int[] {};
	}

}

class SomeBusinessImplStubTest_V2 {

	@Test
	void calculateSumFromDao_Basic() {
		SomeBusinessImpl serv = new SomeBusinessImpl();
		serv.setSomeBusinessdao(new SomeBusinessdaoImplStub());
		int actualResult = serv.calculateSumFromDao();
		int expextedResurlt = 6;
		assertEquals(expextedResurlt, actualResult);
	}

	@Test
	void calculateSumFromDao_emptyArray() {
		SomeBusinessImpl serv = new SomeBusinessImpl();
		serv.setSomeBusinessdao(new SomeBusinessdaoImplEmptyStub());
		int actualResult = serv.calculateSumFromDao();
		int expextedResurlt = 0;
		assertEquals(expextedResurlt, actualResult);
	}

	@Test
	void calculateSumFromDao_oneValue() {
		SomeBusinessImpl serv = new SomeBusinessImpl();
		serv.setSomeBusinessdao(new SomeBusinessdaoImplSingleValueStub());
		int actualResult = serv.calculateSumFromDao();
		int expextedResurlt = 7;
		assertEquals(expextedResurlt, actualResult);
	}

}
