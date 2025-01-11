package tech.ankanroychowdhury.ecommerce.orderline;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public OrderLineService(OrderLineRepository repository, OrderLineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        this.repository.save(order);
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
