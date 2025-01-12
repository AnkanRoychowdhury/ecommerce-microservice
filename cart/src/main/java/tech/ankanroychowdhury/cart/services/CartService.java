package tech.ankanroychowdhury.cart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import tech.ankanroychowdhury.cart.dtos.CartDto;
import tech.ankanroychowdhury.cart.dtos.CartItemDto;
import tech.ankanroychowdhury.cart.dtos.UpdateCartDto;
import tech.ankanroychowdhury.cart.exceptions.CartNotFoundException;
import tech.ankanroychowdhury.cart.exceptions.CartOperationException;
import tech.ankanroychowdhury.cart.exceptions.DuplicateRequestException;
import tech.ankanroychowdhury.cart.exceptions.RedisOperationException;

import java.util.List;


public interface CartService {
    CartDto saveCart(CartDto cartDto) throws CartOperationException;
    CartDto getCartById(String cartId) throws CartNotFoundException;
    CartDto addItemsToCartInDB(String cartId, List<CartItemDto> cartItemsDto) throws CartNotFoundException, CartOperationException;
    void deleteCart(String cartId) throws CartNotFoundException;
    CartDto updateCart(String cartId, UpdateCartDto updateCartDto) throws CartNotFoundException, DuplicateRequestException, CartOperationException;

    // Redis-specific operations
    CartDto saveCartInRedis(CartDto cartDto, String type) throws RedisOperationException;
    CartDto getCartFromRedis(String cartId, String type) throws CartNotFoundException, RedisOperationException, JsonProcessingException;
    CartDto addItemsToCartInRedis(String cartId, String userType, List<CartItemDto> cartItems, CartDto cart) throws RedisOperationException;
}
