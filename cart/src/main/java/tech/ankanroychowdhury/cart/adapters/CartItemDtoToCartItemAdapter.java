package tech.ankanroychowdhury.cart.adapters;

import tech.ankanroychowdhury.cart.dtos.CartItemDto;
import tech.ankanroychowdhury.cart.dtos.UpdateCartItemDto;
import tech.ankanroychowdhury.cart.entities.Cart;
import tech.ankanroychowdhury.cart.entities.CartItem;

import java.util.List;

public interface CartItemDtoToCartItemAdapter {
    CartItem convertToCartItemFromCartItemDto(CartItemDto cartItemDto, Cart cart);

    List<CartItem> convertToCartItemListFromCartItemsDto(List<CartItemDto> cartItemsDto, Cart cart);

    List<CartItem> fromUpdateCartItemDto(List<UpdateCartItemDto> updateCartItemsDto, Cart cart);
}
