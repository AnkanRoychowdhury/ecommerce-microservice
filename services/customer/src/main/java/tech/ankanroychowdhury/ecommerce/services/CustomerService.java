package tech.ankanroychowdhury.ecommerce.services;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tech.ankanroychowdhury.ecommerce.adapters.CustomerMapper;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerRequest;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerResponse;
import tech.ankanroychowdhury.ecommerce.entities.Customer;
import tech.ankanroychowdhury.ecommerce.exceptions.CustomerNotFoundException;
import tech.ankanroychowdhury.ecommerce.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public String createCustomer(CustomerRequest request) {
        Customer customer = this.customerRepository.save(this.customerMapper.toCustomer(request));
        return customer.getId();
    }

    public String updateCustomer(String id, CustomerRequest request) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Unable to update customer:: Customer with id %s not found", id)
                ));
        this.mergeCustomer(customer, request);
        this.customerRepository.save(customer);
        return customer.getId();
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())) customer.setFirstName(request.firstName());
        if(StringUtils.isNotBlank(request.lastName())) customer.setFirstName(request.lastName());
        if(StringUtils.isNotBlank(request.email()) || !customer.getEmail().equals(request.email())) customer.setEmail(request.email());
        if(!customer.getAddress().equals(request.address()) && request.address() != null) customer.setAddress(request.address());
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> allCustomers =  this.customerRepository.findAll();
        return this.customerMapper.toCustomerResponseList(allCustomers);
    }

    public Boolean existsCustomerById(String id) {
        return this.customerRepository.existsById(id);
    }

    public CustomerResponse findCustomerById(String id) {
        return this.customerMapper.toCustomerResponse(this.getCustomerById(id));
    }

    private Customer getCustomerById(String id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Unable to find customer:: Customer with id %s not found", id)
                ));
    }
}
