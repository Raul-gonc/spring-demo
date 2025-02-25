package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerContactTypeTest {

    @Test
    void getIdReturnsCorrectId() {
        CustomerContactType contactType = new CustomerContactType("email");
        contactType.setId(1L);
        assertEquals(1L, contactType.getId());
    }

    @Test
    void setIdSetsCorrectId() {
        CustomerContactType contactType = new CustomerContactType("email");
        contactType.setId(1L);
        assertEquals(1L, contactType.getId());
    }

    @Test
    void getNameReturnsCorrectName() {
        CustomerContactType contactType = new CustomerContactType("email");
        assertEquals("email", contactType.getName());
    }

    @Test
    void setNameSetsCorrectName() {
        CustomerContactType contactType = new CustomerContactType("email");
        contactType.setName("phone");
        assertEquals("phone", contactType.getName());
    }

}