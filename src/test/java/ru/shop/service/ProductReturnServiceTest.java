package ru.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.BadOrderCountException;
import ru.shop.exception.BadProductReturnCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductReturn;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductReturnRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductReturnServiceTest {

    private final ProductReturnRepository productReturnRepository = Mockito.mock();
    private final OrderRepository orderRepository = Mockito.mock();
    private final ProductReturnService productReturnService = new ProductReturnService(productReturnRepository);
    OrderService orderService = Mockito.mock(OrderService.class);

    private UUID orderId;
    private Order order;


    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new Order(orderId, UUID.randomUUID(), UUID.randomUUID(), 10, 1000);
    }

    @Test
    public void shouldAdd() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        productReturnService.add(order, 5);

        verify(productReturnRepository, times(1)).save(any(ProductReturn.class));
    }

    @Test
    public void shouldFindAll() {
        List<ProductReturn> productReturns = List.of(new ProductReturn(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), 4));
        when(productReturnRepository.findAll()).thenReturn(productReturns);
        List<ProductReturn> result = productReturnService.findAll();
        assertEquals(1, result.size());
        verify(productReturnRepository, times(1)).findAll();
    }

    @Test
    void shouldFindByIdProductReturn() {
        UUID id = UUID.randomUUID();
        ProductReturn productReturn = new ProductReturn(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), 3);

        when(productReturnRepository.findById(id)).thenReturn(Optional.of(productReturn));

        ProductReturn result = productReturnService.findById(id);

        assertEquals(productReturn, result);
        verify(productReturnRepository, times(1)).findById(id);
    }

    @Test
    public void shouldThrowWhenProductReturnNotFound() {

        UUID returnId = UUID.randomUUID();

        when(productReturnRepository.findById(returnId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productReturnService.findById(returnId)
        );

        verify(productReturnRepository, times(1)).findById(returnId);
    }

    @Test
    public void shouldFindNotExceptId() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        BadProductReturnCountException exception = assertThrows(
                BadProductReturnCountException.class,
                () -> productReturnService.add(order, 23)
        );

        assertEquals("Return count is greater than ordered count", exception.getMessage());
        verify(productReturnRepository, never()).save(any(ProductReturn.class));
    }
}