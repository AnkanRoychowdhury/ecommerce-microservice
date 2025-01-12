package tech.ankanroychowdhury.cart.adapters;

import org.springframework.stereotype.Component;
import tech.ankanroychowdhury.cart.dtos.CartItemDto;
import tech.ankanroychowdhury.cart.dtos.UpdateCartItemDto;
import tech.ankanroychowdhury.cart.entities.Cart;
import tech.ankanroychowdhury.cart.entities.CartItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemDtoToCartItemAdapterImpl implements CartItemDtoToCartItemAdapter {

    @Override
    public CartItem convertToCartItemFromCartItemDto(CartItemDto cartItemDto, Cart cart) {
        // Build CartItem from CartItemDto and associate with Cart
        return CartItem.builder()
                .productId(cartItemDto.getProductId())
                .quantity(cartItemDto.getQuantity())
                .price(cartItemDto.getPrice())
                .cart(cart) // Establish the relationship
                .build();
    }

    @Override
    public List<CartItem> convertToCartItemListFromCartItemsDto(List<CartItemDto> cartItemsDto, Cart cart) {
        // Convert a list of CartItemDto to CartItem, associating each with the given cart
        return cartItemsDto.stream()
                .map(cartItemDto -> convertToCartItemFromCartItemDto(cartItemDto, cart))
                .collect(Collectors.toList());
    }

    @Override
    public List<CartItem> fromUpdateCartItemDto(List<UpdateCartItemDto> updateCartItemsDto, Cart cart) {
        return updateCartItemsDto.stream()
                .map(updateCartItemDto -> convertUpdateCartDtoToCartItem(updateCartItemDto, cart))
                .collect(Collectors.toList());
    }


    private CartItem convertUpdateCartDtoToCartItem(UpdateCartItemDto cartItemDto, Cart cart) {
        return CartItem.builder()
                .productId(cartItemDto.getProductId())
                .quantity(cartItemDto.getQuantity())
                .price(cartItemDto.getPrice())
                .cart(cart)
                .build();
    }


}
