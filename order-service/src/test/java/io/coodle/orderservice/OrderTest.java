package io.coodle.orderservice;

import io.coodle.orderservice.model.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @BeforeEach
    void setUp() {
        
    }

    @Test
    void testEquality() {
        Assertions.assertEquals(
            Order.builder().id(1L).build(),
            Order.builder().id(1L).build(),
            "testEquality - same id failed"
        );

        assertNotEquals(
            Order.builder().id(1L).build(),
            Order.builder().id(2L).build(),
            "testEquality - different id failed"
        );
    }

    @Test
    @DisplayName("Testing Props should be success")
    void testProps() {
        // Given
        Order order = Order.builder().id(1L).orderNumber("2021-01-19/0000000001").build();

        // Then
        assertAll(
            () -> assertEquals( 1L, order.getId(),"testProps - id failed"),
            () -> assertEquals( "2021-01-19/0000000001", order.getOrderNumber(),"testProps - name failed")
        );
    }

//    @DisplayName("test exception RuntimeException")
//    @Test
//    void testException() {
//        assertThrows(RuntimeException.class, () -> {
//            // call a method
//        });
//    }
}
