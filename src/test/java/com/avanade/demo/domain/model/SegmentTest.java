package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class SegmentTest {

    @Test
    void getIdReturnsCorrectId() {
        Segment segment = new Segment("Retail");
        segment.setId(1L);
        assertEquals(1L, segment.getId());
    }

    @Test
    void setIdSetsCorrectId() {
        Segment segment = new Segment("Retail");
        segment.setId(1L);
        assertEquals(1L, segment.getId());
    }

    @Test
    void getNameReturnsCorrectName() {
        Segment segment = new Segment("Retail");
        assertEquals("Retail", segment.getName());
    }

    @Test
    void setNameSetsCorrectName() {
        Segment segment = new Segment("Retail");
        segment.setName("Wholesale");
        assertEquals("Wholesale", segment.getName());
    }
}