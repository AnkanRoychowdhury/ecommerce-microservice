package tech.ankanroychowdhury.cart.adapters;

import org.springframework.stereotype.Component;
import tech.ankanroychowdhury.cart.dtos.CartDto;
import tech.ankanroychowdhury.cart.dtos.CartItemDto;
import tech.ankanroychowdhury.cart.entities.Cart;
import tech.ankanroychowdhury.cart.entities.CartItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartToCartDtoAdapterImpl implements CartToCartDtoAdapter {

    @Override
    public CartDto convertToCartDto(Cart cart) {
        return CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .cartItems(convertToCartItemDtoList(cart.getCartItems()))
                .build();
    }

    private List<CartItemDto> convertToCartItemDtoList(List<CartItem> cartItems) {
        // Map each CartItem to CartItemDto
        return cartItems.stream()
                .map(this::convertToCartItemDto)
                .collect(Collectors.toList());
    }

    private CartItemDto convertToCartItemDto(CartItem cartItem) {
        // Map CartItem to CartItemDto
        return CartItemDto.builder()
                .itemId(cartItem.getId())
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
