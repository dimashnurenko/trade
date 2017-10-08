package com.trade.domain.order;

import com.trade.common.exception.ResourceNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
	private static final long ORDER_ID = 1L;

	@Mock
	private OrderRepo orderRepo;
	@Mock
	private OrderMapper mapper;

	@InjectMocks
	private OrderServiceImpl service;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void resourceNotFoundExceptionShouldBeThrownWhenOrderNotFound() {
		when(orderRepo.findOne(ORDER_ID)).thenReturn(null);

		exception.expect(ResourceNotFoundException.class);
		exception.expectMessage("Resource with id 1 not found");

		service.findOne(ORDER_ID);
	}

	@Test
	public void resourceNotFoundExceptionShouldBeThrownWhenResourceMapperReturnsNull() {
		when(orderRepo.findOne(ORDER_ID)).thenReturn(new Order());
		when(mapper.map(any(Order.class))).thenReturn(null);

		exception.expect(ResourceNotFoundException.class);
		exception.expectMessage("Resource with id 1 not found");

		service.findOne(ORDER_ID);
	}

	@Test
	public void resourceNotFoundExceptionShouldBeThrownWhenResourceIdNull() {
		exception.expect(ResourceNotFoundException.class);
		exception.expectMessage("Resource id is null");

		service.findOne(null);
	}
}