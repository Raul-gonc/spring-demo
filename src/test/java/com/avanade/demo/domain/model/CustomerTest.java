package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerTest {

    @Test
    void createCustomerWithIdAndName() {
        Customer customer = new Customer(1L, "John Doe");

        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void createCustomerWithNameAndSegment() {
        Segment segment = new Segment("Retail");
        Customer customer = new Customer("John Doe", segment);

        assertEquals("John Doe", customer.getName());
        assertEquals(segment, customer.getSegment());
    }

    @Test
    void createCustomerWithIdNameAndSegment() {
        Segment segment = new Segment("Retail");
        Customer customer = new Customer(1L, "John Doe", segment);

        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals(segment, customer.getSegment());
    }

    @Test
    void setName() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");

        assertEquals("Jane Doe", customer.getName());
    }

    @Test
    void setSegment() {
        Customer customer = new Customer();
        Segment segment = new Segment("Retail");
        customer.setSegment(segment);

        assertEquals(segment, customer.getSegment());
    }

    @Test
    void getId() {
        Customer customer = new Customer();
        customer.setId(1L);

        assertEquals(1L, customer.getId());
    }
}