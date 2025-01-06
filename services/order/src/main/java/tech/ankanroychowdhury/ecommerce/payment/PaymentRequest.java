package tech.ankanroychowdhury.ecommerce.payment;

import tech.ankanroychowdhury.ecommerce.customer.CustomerResponse;
import tech.ankanroychowdhury.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}