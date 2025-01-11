package tech.ankanroychowdhury.ecommerce.adapters;

import org.springframework.stereotype.Component;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerRequest;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerResponse;
import tech.ankanroychowdhury.ecommerce.entities.Customer;

import java.util.List;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public List<CustomerResponse> toCustomerResponseList(List<Customer> allCustomers) {
        return allCustomers.stream()
                .map(this::toCustomerResponse)
                .toList();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
