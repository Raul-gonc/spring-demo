package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class DocumentTypeTest {
    @Test
    void constructorSetsNameCorrectly() {
        DocumentType documentType = new DocumentType("passport");
        assertEquals("passport", documentType.getName());
    }

    @Test
    void getIdReturnsCorrectId() {
        DocumentType documentType = new DocumentType("passport");
        documentType.setId(1L);
        assertEquals(1L, documentType.getId());
    }

    @Test
    void setIdSetsCorrectId() {
        DocumentType documentType = new DocumentType("passport");
        documentType.setId(1L);
        assertEquals(1L, documentType.getId());
    }

    @Test
    void getNameReturnsCorrectName() {
        DocumentType documentType = new DocumentType("passport");
        assertEquals("passport", documentType.getName());
    }

    @Test
    void setNameSetsCorrectName() {
        DocumentType documentType = new DocumentType("passport");
        documentType.setName("driver's license");
        assertEquals("driver's license", documentType.getName());
    }
}