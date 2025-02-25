package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.domain.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CustomerControllerTests {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void getCustomerByIdReturnsCustomer() {
        CustomerDTO mockCustomer = new CustomerDTO(1L, "John Doe", "retail", null, null);
        when(customerService.getCustomerById(1L)).thenReturn(mockCustomer);

        CustomerDTO response = customerController.getCustomerById(1L);

        assertThat(response).isEqualTo(mockCustomer);
    }

    @Test
    void getCustomerByNameReturnsCustomer() {
        CustomerDTO mockCustomer = new CustomerDTO(1L, "John Doe", "retail", null, null);
        when(customerService.getCustomerByName("John")).thenReturn(mockCustomer);

        CustomerDTO response = customerController.getCustomerByName("John");

        assertThat(response).isEqualTo(mockCustomer);
    }

    @Test
    void getCustomerDocumentsByIdReturnsDocuments() {
        List<CustomerDocumentDTO> mockDocuments = List.of(new CustomerDocumentDTO("Passport", "123456789"));
        when(customerService.getDocumentsByUserId(1L)).thenReturn(mockDocuments);

        List<CustomerDocumentDTO> response = customerController.getDocumentsByUserId(1L);

        assertThat(response).isEqualTo(mockDocuments);
    }

    @Test
    void getCustomerContactsByIdReturnsContacts() {
        List<CustomerContactDTO> mockContacts = List.of(new CustomerContactDTO("John Doe", "123-456-7890"));
        when(customerService.getContactsByUserId(1L)).thenReturn(mockContacts);

        List<CustomerContactDTO> response = customerController.getContactsByUserId(1L);

        assertThat(response).isEqualTo(mockContacts);
    }

    @Test
    void saveCustomerCallsService() {
        CustomerRequestDTO mockCustomerRequestDto = new CustomerRequestDTO("John Doe", "Retail", "john.doe@example.com", "12345678901");

        customerController.saveCustomer(mockCustomerRequestDto);

        CustomerRequestDTO expectedCustomerRequestDto = new CustomerRequestDTO(
                mockCustomerRequestDto.name(),
                mockCustomerRequestDto.segmentName(),
                mockCustomerRequestDto.email(),
                mockCustomerRequestDto.cpf()
        );
        verify(customerService, times(1)).saveCustomer(expectedCustomerRequestDto);
    }
}