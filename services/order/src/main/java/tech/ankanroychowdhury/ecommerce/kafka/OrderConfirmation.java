package tech.ankanroychowdhury.ecommerce.kafka;

import tech.ankanroychowdhury.ecommerce.customer.CustomerResponse;
import tech.ankanroychowdhury.ecommerce.order.PaymentMethod;
import tech.ankanroychowdhury.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
