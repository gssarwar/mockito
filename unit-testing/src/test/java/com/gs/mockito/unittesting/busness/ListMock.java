package com.gs.mockito.unittesting.busness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

class ListMock {

	List<String> mock = mock(List.class);

	@Test
	void list_size_test_basic() { 
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	void list_size_test_returnMultipleValue() {
		when(mock.size()).thenReturn(5).thenReturn(10).thenReturn(20);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
		assertEquals(20, mock.size());
	}

	@Test
	void list_returnWithParameter() {
		when(mock.get(0)).thenReturn("Gulam").thenReturn("Sarwar"); 
		assertEquals("Gulam", mock.get(0));
		assertEquals("Sarwar", mock.get(0));
	}

	@Test
	void list_returnWithGenericParameter() {
		when(mock.get(ArgumentMatchers.anyInt())).thenReturn("Gulam").thenReturn("Sarwar");
		assertEquals("Gulam", mock.get(0));
		assertEquals("Sarwar", mock.get(10));
		assertEquals("Sarwar", mock.get(5));
	}

	@Test
	void verifyArgumentValuePassedInMethodCall() {
		// SUT
		String value1 = mock.get(0);
		String value2 = mock.get(1);
		// String value2 = mock.get(ArgumentMatchers.anyInt());

		verify(mock).get(0);// verify wether mock.get is called or not
		// verify(mock,times(2)).get(ArgumentMatchers.anyInt());////verify wether
		// mock.get is called or not
		verify(mock, times(2)).get(ArgumentMatchers.anyInt());// verify whether how many times mock.get is called
		verify(mock, atLeast(1)).get(ArgumentMatchers.anyInt());// verify whether how many times mock.get is called
		verify(mock, atLeastOnce()).get(ArgumentMatchers.anyInt());// verify whether how many times mock.get is called
		verify(mock, atMost(2)).get(ArgumentMatchers.anyInt());// verify whether how many times mock.get is called
		verify(mock, never()).get(2);// verify whether how many times mock.get is called
		verify(mock, never()).get(2);// verify whether how many times mock.get is called
	}

	@Test
	public void argumentCapturing() {
		// SUT(system under test )
		mock.add("Sarwar");// to verify what is added to mock list

		// verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());
		assertEquals("Sarwar", captor.getValue());

	}

	@Test
	public void multipleArgumentCapturing() {
		// SUT(system under test )
		mock.add("Sarwar1");// to verify what is added to mock list
		mock.add("Sarwar2");// to verify what is added to mock list

		// verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//		System.out.println(captor.capture());
		verify(mock, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();

		assertEquals("Sarwar1", allValues.get(0));
		assertEquals("Sarwar2", allValues.get(1));

	}

	@Test
	public void mocking() {
		ArrayList arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));// null
		System.out.println(arrayListMock.size());// 0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());// 0
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());// 5
	}

	@Test
	public void spying() {
		ArrayList arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0));// Test0
		System.out.println(arrayListSpy.size());// 1
		arrayListSpy.add("Test");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());// 3

		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());// 5

		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size());// 5

		verify(arrayListSpy).add("Test4");
	}

}
