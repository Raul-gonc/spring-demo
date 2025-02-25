package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class CustomerDocumentTest {

    @Test
    void createCustomerDocumentWithValidData() {
        Customer customer = new Customer(1L, "John Doe");
        DocumentType documentType = new DocumentType("passport");
        CustomerDocument document = new CustomerDocument(customer, "123456789", documentType);

        assertEquals("123456789", document.getDocument());
        assertEquals(customer, document.getCustomer());
        assertEquals("passport", document.getDocumentTypeName());
    }

    @Test
    void setDocument() {
        CustomerDocument document = new CustomerDocument();
        document.setDocument("987654321");

        assertEquals("987654321", document.getDocument());
    }

    @Test
    void setCustomer() {
        CustomerDocument document = new CustomerDocument();
        Customer customer = new Customer(1L, "Jane Doe");
        document.setCustomer(customer);

        assertEquals(customer, document.getCustomer());
    }

    @Test
    void setDocumentType() {
        CustomerDocument document = new CustomerDocument();
        DocumentType documentType = new DocumentType("passport");
        document.setDocumentType(documentType);

        assertEquals(documentType, document.getDocumentType());
    }

    @Test
    void setId() {
        CustomerDocument document = new CustomerDocument();
        document.setId(1L);

        assertEquals(1L, document.getId());
    }
}