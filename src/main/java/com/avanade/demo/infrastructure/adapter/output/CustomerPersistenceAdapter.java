package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.model.Customer;
import com.avanade.demo.domain.model.CustomerContact;
import com.avanade.demo.domain.model.CustomerDocument;
import com.avanade.demo.infrastructure.adapter.output.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CustomerContactTypeRepository customerContactTypeRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer cust = customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        final Customer cust = customerRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public List<CustomerContactDTO> getContactsByUserId(Long id) {
        final List<CustomerContact> contacts = customerContactRepository.findByCustomerId(id);
        return contacts.stream()
                .map(contact -> new CustomerContactDTO(contact.getContactValue(), contact.getCustomerContactType().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDocumentDTO> getDocumentsByUserId(Long id) {
        final List<CustomerDocument> documents = customerDocumentRepository.findByCustomerId(id);
        return documents.stream()
                .map(document -> new CustomerDocumentDTO(document.getDocument(), document.getDocumentTypeName()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCustomer(CustomerRequestDTO updatedCustomerRequestDto) {
        Customer customer = new Customer(updatedCustomerRequestDto.name(),
                segmentRepository.findByName(updatedCustomerRequestDto.segmentName()).orElseThrow(() ->
                        new EntityNotFoundException("Segmento não encontrado")));
        customerRepository.save(customer);
        saveCustomerContact(customer, updatedCustomerRequestDto.email());
        saveCustomerDocument(customer, updatedCustomerRequestDto.cpf());
    }

    private void saveCustomerContact(Customer customer, String email) {
        CustomerContact customerContact = new CustomerContact(customer, email,
                customerContactTypeRepository.findByName("e-mail").orElseThrow(() ->
                        new EntityNotFoundException("Contact type not found")));
        customerContactRepository.save(customerContact);
    }

    private void saveCustomerDocument(Customer customer, String cpf) {
        CustomerDocument customerDocument = new CustomerDocument(customer, cpf,
                documentTypeRepository.findByName("cpf").orElseThrow(() ->
                        new EntityNotFoundException("Document type not found")));
        customerDocumentRepository.save(customerDocument);
    }
}
