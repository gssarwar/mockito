package com.gs.mockito.unittesting.busness;

import com.gs.mockito.unittesting.dao.SomeBusinessDao;

public class SomeBusinessImpl {
	
	SomeBusinessDao someBusinessdao = null;
	
	
	public void setSomeBusinessdao(SomeBusinessDao someBusinessdao) {
		this.someBusinessdao = someBusinessdao;
	}

	public int calculateSum(int[] data) {
		int sum = 0 ;
		for(int val : data) {
			sum = sum + val ;
		}
		return sum;
	}
	
	public int calculateSumFromDao() {
		int sum = 0 ;
		int data[] = someBusinessdao.retriveAllData();
		for(int val : data) {
			sum = sum + val ;
		}
		//someBusinessdao.retriveAllData(sum)//SUT
		//argument value passed in internal method call can be verified 
		//This can be verified 
		return sum;
	}

}
