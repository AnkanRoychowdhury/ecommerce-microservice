package tech.ankanroychowdhury.cart.adapters;

import tech.ankanroychowdhury.cart.dtos.CartDto;
import tech.ankanroychowdhury.cart.entities.Cart;

public interface CartDtoToCartAdapter {
    public Cart convertToCartFromCartDto(CartDto cartDto);
}
