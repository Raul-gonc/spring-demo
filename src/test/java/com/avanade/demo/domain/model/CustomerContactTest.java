package com.avanade.demo.domain.model;

import org.hibernate.engine.spi.SelfDirtinessTracker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerContactTest {

    @Test
    void createCustomerContactWithValidData() {
        Customer customer = new Customer(1L, "John Doe");
        CustomerContactType contactType = new CustomerContactType("e-mail");
        CustomerContact contact = new CustomerContact(customer, "john.doe@example.com", contactType);

        assertEquals("john.doe@example.com", contact.getContactValue());
        assertEquals(customer, contact.getCustomer());
        assertEquals(contactType, contact.getCustomerContactType());
    }

    @Test
    void getIdReturnsCorrectId() {
        CustomerContact contact = new CustomerContact();
        contact.setId(1L);
        assertEquals(1L, contact.getId());
    }

    @Test
    void setIdSetsCorrectId() {
        CustomerContact contact = new CustomerContact();
        contact.setId(1L);
        assertEquals(1L, contact.getId());
    }

    @Test
    void getCustomerReturnsCorrectCustomer() {
        Customer customer = new Customer(1L, "John Doe");
        CustomerContact contact = new CustomerContact();
        contact.setCustomer(customer);
        assertEquals(customer, contact.getCustomer());
    }

    @Test
    void setCustomerSetsCorrectCustomer() {
        Customer customer = new Customer(1L, "John Doe");
        CustomerContact contact = new CustomerContact();
        contact.setCustomer(customer);
        assertEquals(customer, contact.getCustomer());
    }

    @Test
    void getCustomerContactTypeReturnsCorrectType() {
        CustomerContactType contactType = new CustomerContactType("e-mail");
        CustomerContact contact = new CustomerContact();
        contact.setCustomerContactType(contactType);
        assertEquals(contactType, contact.getCustomerContactType());
    }

    @Test
    void setCustomerContactTypeSetsCorrectType() {
        CustomerContactType contactType = new CustomerContactType("e-mail");
        CustomerContact contact = new CustomerContact();
        contact.setCustomerContactType(contactType);
        assertEquals(contactType, contact.getCustomerContactType());
    }

    @Test
    void getContactValueReturnsCorrectValue() {
        CustomerContact contact = new CustomerContact();
        contact.setContactValue("john.doe@example.com");
        assertEquals("john.doe@example.com", contact.getContactValue());
    }

    @Test
    void setContactValueSetsCorrectValue() {
        CustomerContact contact = new CustomerContact();
        contact.setContactValue("john.doe@example.com");
        assertEquals("john.doe@example.com", contact.getContactValue());
    }

}