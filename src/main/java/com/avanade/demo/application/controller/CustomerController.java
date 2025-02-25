package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.domain.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/cliente/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        logger.info("Found customer with id: " + id);
        return customer;
    }

    @GetMapping("/cliente/name/{customerName}")
    public CustomerDTO getCustomerByName(@PathVariable String customerName) {
        CustomerDTO customer = customerService.getCustomerByName(customerName);
        logger.info("Found customer with name: " + customerName);
        return customer;
    }

    @GetMapping("/cliente/contacts/{id}")
    public List<CustomerContactDTO> getContactsByUserId(@PathVariable long id) {
        List<CustomerContactDTO> contacts = customerService.getContactsByUserId(id);
        logger.info("Found contacts for customer with id: " + id);
        return contacts;
    }

    @GetMapping("/cliente/documents/{id}")
    public List<CustomerDocumentDTO> getDocumentsByUserId(@PathVariable long id) {
        List<CustomerDocumentDTO> documents = customerService.getDocumentsByUserId(id);
        logger.info("Found documents for customer with id: " + id);
        return documents;
    }

    @PostMapping("/cliente/")
    public void saveCustomer(@RequestBody CustomerRequestDTO customerRequestDto) {
        CustomerRequestDTO updatedCustomerRequestDto = new CustomerRequestDTO(customerRequestDto.name(), customerRequestDto.segmentName(), customerRequestDto.email(), customerRequestDto.cpf());
        customerService.saveCustomer(updatedCustomerRequestDto);
        logger.info("Saved customer");
    }
}