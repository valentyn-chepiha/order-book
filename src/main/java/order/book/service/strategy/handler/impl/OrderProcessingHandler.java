package order.book.service.strategy.handler.impl;

import java.util.Arrays;
import order.book.model.types.TypeOrder;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class OrderProcessingHandler implements ProcessingHandler {
    private static final int INDEX_TYPE_ORDER = 0;
    private final TypeProcessingStrategy<TypeOrder> orderProcessingStrategy;

    public OrderProcessingHandler(TypeProcessingStrategy<TypeOrder> orderProcessingStrategy) {
        this.orderProcessingStrategy = orderProcessingStrategy;
    }

    @Override
    public void processing(String[] params) {
        TypeOrder typeOrder = Arrays.stream(TypeOrder.values())
                .filter(o -> o.getShortName().equals(params[INDEX_TYPE_ORDER]))
                .findFirst()
                .orElse(null);
        if (typeOrder == null) {
            return;
        }
        ProcessingHandler handler = orderProcessingStrategy.getHandlerByTypeProcessing(typeOrder);
        handler.processing(Arrays.copyOfRange(params, 1, params.length));
    }
}
