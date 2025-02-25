package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceTest {

    @Mock
    private CustomerOutput customerOutput;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerByIdReturnsCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john.doe@example.com", null, null);
        when(customerOutput.getCustomerById(1L)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals(customerDTO, result);
        verify(customerOutput, times(1)).getCustomerById(1L);
    }

    @Test
    void getCustomerByNameReturnsCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john.doe@example.com", null, null);
        when(customerOutput.getCustomerByName("John Doe")).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerByName("John Doe");

        assertEquals(customerDTO, result);
        verify(customerOutput, times(1)).getCustomerByName("John Doe");
    }

    @Test
    void getContactsByUserIdReturnsListOfCustomerContactDTO() {
        List<CustomerContactDTO> contacts = List.of(new CustomerContactDTO("John Doe", "123-456-7890"));
        when(customerOutput.getContactsByUserId(1L)).thenReturn(contacts);

        List<CustomerContactDTO> result = customerService.getContactsByUserId(1L);

        assertEquals(contacts, result);
        verify(customerOutput, times(1)).getContactsByUserId(1L);
    }

    @Test
    void getDocumentsByUserIdReturnsListOfCustomerDocumentDTO() {
        List<CustomerDocumentDTO> documents = List.of(new CustomerDocumentDTO("Passport", "123456789"));
        when(customerOutput.getDocumentsByUserId(1L)).thenReturn(documents);

        List<CustomerDocumentDTO> result = customerService.getDocumentsByUserId(1L);

        assertEquals(documents, result);
        verify(customerOutput, times(1)).getDocumentsByUserId(1L);
    }

    @Test
    void saveCustomerCallsCustomerOutputSaveCustomer() {
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("John Doe", "john.doe@example.com", "123-456-7890", "12312312");

        customerService.saveCustomer(customerRequestDTO);

        verify(customerOutput, times(1)).saveCustomer(customerRequestDTO);
    }
}
