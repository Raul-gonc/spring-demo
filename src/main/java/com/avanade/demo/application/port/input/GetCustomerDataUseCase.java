package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.dto.CustomerRequestDTO;

import java.util.List;

public interface GetCustomerDataUseCase {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String username);

    List<CustomerContactDTO> getContactsByUserId(Long id);

    List<CustomerDocumentDTO> getDocumentsByUserId(Long id);

    void saveCustomer(CustomerRequestDTO updatedCustomerRequestDto);
}