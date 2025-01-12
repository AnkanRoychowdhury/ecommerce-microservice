package tech.ankanroychowdhury.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ankanroychowdhury.cart.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCartIdAndActiveIsTrue(String cartId);
}
