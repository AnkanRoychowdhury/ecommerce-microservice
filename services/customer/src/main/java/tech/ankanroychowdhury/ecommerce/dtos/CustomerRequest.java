package tech.ankanroychowdhury.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import tech.ankanroychowdhury.ecommerce.entities.Address;

public record CustomerRequest(
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required") @Email(message = "Email is invalid")
        String email,
        Address address
) { }
