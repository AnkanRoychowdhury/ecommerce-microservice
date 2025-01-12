package tech.ankanroychowdhury.cart.adapters;

import tech.ankanroychowdhury.cart.dtos.CartDto;
import tech.ankanroychowdhury.cart.entities.Cart;

public interface CartToCartDtoAdapter {
    CartDto convertToCartDto(Cart cart);
}
