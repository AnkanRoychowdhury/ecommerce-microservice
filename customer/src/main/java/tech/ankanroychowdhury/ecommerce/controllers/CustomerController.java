package tech.ankanroychowdhury.ecommerce.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerRequest;
import tech.ankanroychowdhury.ecommerce.dtos.CustomerResponse;
import tech.ankanroychowdhury.ecommerce.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(this.customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest, @RequestParam String id) {
        return ResponseEntity.ok(this.customerService.updateCustomer(id, customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(this.customerService.existsCustomerById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String id) {
        return ResponseEntity.ok(this.customerService.findCustomerById(id));
    }
}
