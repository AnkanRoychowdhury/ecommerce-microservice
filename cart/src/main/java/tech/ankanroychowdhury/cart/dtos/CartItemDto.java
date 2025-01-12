package tech.ankanroychowdhury.cart.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import tech.ankanroychowdhury.cart.entities.CartItem;

import java.io.Serializable;

/**
 * DTO for {@link CartItem}
 */
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Schema(hidden = true)
public class CartItemDto implements Serializable {

    Long itemId;

    @NotNull(message = "Product ID should not be empty")
    @Size
    @NotEmpty(message = "Product ID should not be empty")
    @NotBlank(message = "Product ID should not be empty")
    String productId;

    @Min(message = "Minimum Quantity should be at least 1", value = 1)
    @Max(message = "You can add max of 5 products at a time", value = 5)
    @Positive(message = "Quantity should not be lesser than 1")
    int quantity;

    @Min(message = "Minimum Price should be at least 0", value = 0)
    @Positive(message = "Price should not be in negative")
    double price;
}