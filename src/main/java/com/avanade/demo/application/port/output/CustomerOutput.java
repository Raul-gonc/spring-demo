package com.avanade.demo.application.port.output;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;

import java.util.List;

public interface CustomerOutput {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String name);

    List<CustomerContactDTO> getContactsByUserId(Long id);

    List<CustomerDocumentDTO> getDocumentsByUserId(Long id);

    void saveCustomer(CustomerRequestDTO updatedCustomerRequestDto);
}
