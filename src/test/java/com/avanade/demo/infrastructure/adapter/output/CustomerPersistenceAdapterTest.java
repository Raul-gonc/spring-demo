package com.avanade.demo.infrastructure.adapter.output;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.domain.exception.EntityAlreadyExistsException;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.model.*;
import com.avanade.demo.infrastructure.adapter.output.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class CustomerPersistenceAdapterTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerContactRepository customerContactRepository;

    @Mock
    private CustomerDocumentRepository customerDocumentRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @Mock
    private CustomerContactTypeRepository customerContactTypeRepository;

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @InjectMocks
    private CustomerPersistenceAdapter customerPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerById_returnsCustomerDTO_whenCustomerExists() {
        Customer customer = new Customer(1L, "John Doe", new Segment("Retail"));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerPersistenceAdapter.getCustomerById(1L);

        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("Retail", result.segmentName());
    }

    @Test
    void getCustomerById_throwsEntityNotFoundException_whenCustomerDoesNotExist() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> customerPersistenceAdapter.getCustomerById(1L));
    }

    @Test
    void getCustomerByName_returnsCustomerDTO_whenCustomerExists() {
        Customer customer = new Customer(1L, "John Doe", new Segment("Retail"));
        when(customerRepository.findByName("John Doe")).thenReturn(Optional.of(customer));

        CustomerDTO result = customerPersistenceAdapter.getCustomerByName("John Doe");

        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        assertEquals("Retail", result.segmentName());
    }

    @Test
    void getCustomerByName_throwsEntityNotFoundException_whenCustomerDoesNotExist() {
        when(customerRepository.findByName("John Doe")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> customerPersistenceAdapter.getCustomerByName("John Doe"));
    }

    @Test
    void getContactsByUserId_returnsListOfCustomerContactDTO_whenContactsExist() {
        CustomerContact contact = new CustomerContact(new Customer(), "john.doe@example.com", new CustomerContactType("e-mail"));
        when(customerContactRepository.findByCustomerId(1L)).thenReturn(List.of(contact));

        List<CustomerContactDTO> result = customerPersistenceAdapter.getContactsByUserId(1L);

        assertEquals(1, result.size());
        assertEquals("john.doe@example.com", result.get(0).contactValue());
        assertEquals("e-mail", result.get(0).contactType());
    }

    @Test
    void getContactsByUserId_returnsEmptyList_whenNoContactsExist() {
        when(customerContactRepository.findByCustomerId(1L)).thenReturn(Collections.emptyList());

        List<CustomerContactDTO> result = customerPersistenceAdapter.getContactsByUserId(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void getDocumentsByUserId_returnsListOfCustomerDocumentDTO_whenDocumentsExist() {
        CustomerDocument document = new CustomerDocument(new Customer(), "123456789", new DocumentType("cpf"));
        when(customerDocumentRepository.findByCustomerId(1L)).thenReturn(List.of(document));

        List<CustomerDocumentDTO> result = customerPersistenceAdapter.getDocumentsByUserId(1L);

        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).documentNumber());
        assertEquals("cpf", result.get(0).documentType());
    }

    @Test
    void getDocumentsByUserId_returnsEmptyList_whenNoDocumentsExist() {
        when(customerDocumentRepository.findByCustomerId(1L)).thenReturn(Collections.emptyList());

        List<CustomerDocumentDTO> result = customerPersistenceAdapter.getDocumentsByUserId(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveCustomer_savesCustomerAndRelatedEntities_whenValidRequest() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("John Doe", "Retail", "john.doe@example.com", "123456789");
        Segment segment = new Segment("Retail");
        CustomerContactType contactType = new CustomerContactType("e-mail");
        DocumentType documentType = new DocumentType("cpf");

        when(segmentRepository.findByName("Retail")).thenReturn(Optional.of(segment));
        when(customerContactTypeRepository.findByName("e-mail")).thenReturn(Optional.of(contactType));
        when(documentTypeRepository.findByName("cpf")).thenReturn(Optional.of(documentType));

        customerPersistenceAdapter.saveCustomer(requestDTO);

        verify(customerRepository).save(any(Customer.class));
        verify(customerContactRepository).save(any(CustomerContact.class));
        verify(customerDocumentRepository).save(any(CustomerDocument.class));
    }

    @Test
    void saveCustomer_throwsEntityNotFoundException_whenSegmentNotFound() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("John Doe", "Retail", "john.doe@example.com", "123456789");

        when(segmentRepository.findByName("Retail")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> customerPersistenceAdapter.saveCustomer(requestDTO));
    }
}