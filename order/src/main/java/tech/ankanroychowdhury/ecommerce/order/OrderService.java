package tech.ankanroychowdhury.ecommerce.order;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.ankanroychowdhury.ecommerce.customer.CustomerClient;
import tech.ankanroychowdhury.ecommerce.exceptions.BusinessException;
import tech.ankanroychowdhury.ecommerce.kafka.OrderConfirmation;
import tech.ankanroychowdhury.ecommerce.kafka.OrderProducer;
import tech.ankanroychowdhury.ecommerce.orderline.OrderLineRequest;
import tech.ankanroychowdhury.ecommerce.orderline.OrderLineService;
import tech.ankanroychowdhury.ecommerce.payment.PaymentClient;
import tech.ankanroychowdhury.ecommerce.payment.PaymentRequest;
import tech.ankanroychowdhury.ecommerce.product.PurchaseRequest;
import tech.ankanroychowdhury.ecommerce.product.ProductClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    public OrderService(OrderRepository repository, OrderMapper mapper, CustomerClient customerClient, PaymentClient paymentClient, ProductClient productClient, OrderLineService orderLineService, OrderProducer orderProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.customerClient = customerClient;
        this.paymentClient = paymentClient;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
    }

    @Transactional
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
