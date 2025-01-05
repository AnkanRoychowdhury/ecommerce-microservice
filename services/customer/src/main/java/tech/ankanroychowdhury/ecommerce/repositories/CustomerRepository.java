package tech.ankanroychowdhury.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.ankanroychowdhury.ecommerce.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
