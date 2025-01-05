package tech.ankanroychowdhury.ecommerce.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.ankanroychowdhury.ecommerce.entities.Address;

@Builder
@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
//    private String street;
//    private String houseNumber;
//    private String zip;
    private Address address;
}
