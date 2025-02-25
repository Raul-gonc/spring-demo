package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;
import com.avanade.demo.application.port.input.GetCustomerDataUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService implements GetCustomerDataUseCase {

    @Autowired
    private CustomerOutput customerOutput;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerOutput.getCustomerById(id);
    }

    @Override
    public CustomerDTO getCustomerByName(String customerName) {
        return customerOutput.getCustomerByName(customerName);
    }

    @Override
    public List<CustomerContactDTO> getContactsByUserId(Long id) {
        return customerOutput.getContactsByUserId(id);
    }

    @Override
    public List<CustomerDocumentDTO> getDocumentsByUserId(Long id) {
        return  customerOutput.getDocumentsByUserId(id);
    }

    @Override
    public void saveCustomer(CustomerRequestDTO updatedCustomerRequestDto) {
        customerOutput.saveCustomer(updatedCustomerRequestDto);
    }
}
